package com.capgemini.web.rest;

import com.capgemini.SmartbankApp;
import com.capgemini.domain.Bill;
import com.capgemini.repository.BillRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the BillResource REST controller.
 *
 * @see BillResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SmartbankApp.class)
@WebAppConfiguration
@IntegrationTest
public class BillResourceIntTest {


    private static final Double DEFAULT_DUE_AMOUNT = 1D;
    private static final Double UPDATED_DUE_AMOUNT = 2D;

    private static final LocalDate DEFAULT_DUE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DUE_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_DETAILS = "AAAAA";
    private static final String UPDATED_DETAILS = "BBBBB";
    private static final String DEFAULT_BILLER_NAME = "AAAAA";
    private static final String UPDATED_BILLER_NAME = "BBBBB";

    @Inject
    private BillRepository billRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBillMockMvc;

    private Bill bill;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BillResource billResource = new BillResource();
        ReflectionTestUtils.setField(billResource, "billRepository", billRepository);
        this.restBillMockMvc = MockMvcBuilders.standaloneSetup(billResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        bill = new Bill();
        bill.setDueAmount(DEFAULT_DUE_AMOUNT);
        bill.setDueDate(DEFAULT_DUE_DATE);
        bill.setDetails(DEFAULT_DETAILS);
        bill.setBillerName(DEFAULT_BILLER_NAME);
    }

    @Test
    @Transactional
    public void createBill() throws Exception {
        int databaseSizeBeforeCreate = billRepository.findAll().size();

        // Create the Bill

        restBillMockMvc.perform(post("/api/bills")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(bill)))
                .andExpect(status().isCreated());

        // Validate the Bill in the database
        List<Bill> bills = billRepository.findAll();
        assertThat(bills).hasSize(databaseSizeBeforeCreate + 1);
        Bill testBill = bills.get(bills.size() - 1);
        assertThat(testBill.getDueAmount()).isEqualTo(DEFAULT_DUE_AMOUNT);
        assertThat(testBill.getDueDate()).isEqualTo(DEFAULT_DUE_DATE);
        assertThat(testBill.getDetails()).isEqualTo(DEFAULT_DETAILS);
        assertThat(testBill.getBillerName()).isEqualTo(DEFAULT_BILLER_NAME);
    }

    @Test
    @Transactional
    public void getAllBills() throws Exception {
        // Initialize the database
        billRepository.saveAndFlush(bill);

        // Get all the bills
        restBillMockMvc.perform(get("/api/bills?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(bill.getId().intValue())))
                .andExpect(jsonPath("$.[*].dueAmount").value(hasItem(DEFAULT_DUE_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].dueDate").value(hasItem(DEFAULT_DUE_DATE.toString())))
                .andExpect(jsonPath("$.[*].details").value(hasItem(DEFAULT_DETAILS.toString())))
                .andExpect(jsonPath("$.[*].billerName").value(hasItem(DEFAULT_BILLER_NAME.toString())));
    }

    @Test
    @Transactional
    public void getBill() throws Exception {
        // Initialize the database
        billRepository.saveAndFlush(bill);

        // Get the bill
        restBillMockMvc.perform(get("/api/bills/{id}", bill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(bill.getId().intValue()))
            .andExpect(jsonPath("$.dueAmount").value(DEFAULT_DUE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.dueDate").value(DEFAULT_DUE_DATE.toString()))
            .andExpect(jsonPath("$.details").value(DEFAULT_DETAILS.toString()))
            .andExpect(jsonPath("$.billerName").value(DEFAULT_BILLER_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBill() throws Exception {
        // Get the bill
        restBillMockMvc.perform(get("/api/bills/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBill() throws Exception {
        // Initialize the database
        billRepository.saveAndFlush(bill);
        int databaseSizeBeforeUpdate = billRepository.findAll().size();

        // Update the bill
        Bill updatedBill = new Bill();
        updatedBill.setId(bill.getId());
        updatedBill.setDueAmount(UPDATED_DUE_AMOUNT);
        updatedBill.setDueDate(UPDATED_DUE_DATE);
        updatedBill.setDetails(UPDATED_DETAILS);
        updatedBill.setBillerName(UPDATED_BILLER_NAME);

        restBillMockMvc.perform(put("/api/bills")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedBill)))
                .andExpect(status().isOk());

        // Validate the Bill in the database
        List<Bill> bills = billRepository.findAll();
        assertThat(bills).hasSize(databaseSizeBeforeUpdate);
        Bill testBill = bills.get(bills.size() - 1);
        assertThat(testBill.getDueAmount()).isEqualTo(UPDATED_DUE_AMOUNT);
        assertThat(testBill.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
        assertThat(testBill.getDetails()).isEqualTo(UPDATED_DETAILS);
        assertThat(testBill.getBillerName()).isEqualTo(UPDATED_BILLER_NAME);
    }

    @Test
    @Transactional
    public void deleteBill() throws Exception {
        // Initialize the database
        billRepository.saveAndFlush(bill);
        int databaseSizeBeforeDelete = billRepository.findAll().size();

        // Get the bill
        restBillMockMvc.perform(delete("/api/bills/{id}", bill.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Bill> bills = billRepository.findAll();
        assertThat(bills).hasSize(databaseSizeBeforeDelete - 1);
    }
}
