package com.capgemini.web.rest;

import com.capgemini.SmartbankApp;
import com.capgemini.domain.BAccount;
import com.capgemini.repository.BAccountRepository;

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
 * Test class for the BAccountResource REST controller.
 *
 * @see BAccountResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SmartbankApp.class)
@WebAppConfiguration
@IntegrationTest
public class BAccountResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAA";
    private static final String UPDATED_TYPE = "BBBBB";
    private static final double DEFAULT_BALANCE = 1000;
    private static final double UPDATED_BALANCE =10000;

    private static final LocalDate DEFAULT_DATE_CREATED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREATED = LocalDate.now(ZoneId.systemDefault());

    @Inject
    private BAccountRepository bAccountRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBAccountMockMvc;

    private BAccount bAccount;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BAccountResource bAccountResource = new BAccountResource();
        ReflectionTestUtils.setField(bAccountResource, "bAccountRepository", bAccountRepository);
        this.restBAccountMockMvc = MockMvcBuilders.standaloneSetup(bAccountResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        bAccount = new BAccount();
        bAccount.setType(DEFAULT_TYPE);
        bAccount.setBalance(DEFAULT_BALANCE);
        bAccount.setDateCreated(DEFAULT_DATE_CREATED);
    }

    @Test
    @Transactional
    public void createBAccount() throws Exception {
        int databaseSizeBeforeCreate = bAccountRepository.findAll().size();

        // Create the BAccount

        restBAccountMockMvc.perform(post("/api/b-accounts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(bAccount)))
                .andExpect(status().isCreated());

        // Validate the BAccount in the database
        List<BAccount> bAccounts = bAccountRepository.findAll();
        assertThat(bAccounts).hasSize(databaseSizeBeforeCreate + 1);
        BAccount testBAccount = bAccounts.get(bAccounts.size() - 1);
        assertThat(testBAccount.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testBAccount.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testBAccount.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
    }

    @Test
    @Transactional
    public void getAllBAccounts() throws Exception {
        // Initialize the database
        bAccountRepository.saveAndFlush(bAccount);

        // Get all the bAccounts
        restBAccountMockMvc.perform(get("/api/b-accounts?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(bAccount.getId().intValue())))
                .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
                .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED.toString())));
    }

    @Test
    @Transactional
    public void getBAccount() throws Exception {
        // Initialize the database
        bAccountRepository.saveAndFlush(bAccount);

        // Get the bAccount
        restBAccountMockMvc.perform(get("/api/b-accounts/{id}", bAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(bAccount.getId().intValue()))
            .andExpect(jsonPath("$.dateCreated").value(DEFAULT_DATE_CREATED.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBAccount() throws Exception {
        // Get the bAccount
        restBAccountMockMvc.perform(get("/api/b-accounts/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBAccount() throws Exception {
        // Initialize the database
        bAccountRepository.saveAndFlush(bAccount);
        int databaseSizeBeforeUpdate = bAccountRepository.findAll().size();

        // Update the bAccount
        BAccount updatedBAccount = new BAccount();
        updatedBAccount.setId(bAccount.getId());
        updatedBAccount.setType(UPDATED_TYPE);
        updatedBAccount.setBalance(UPDATED_BALANCE);
        updatedBAccount.setDateCreated(UPDATED_DATE_CREATED);

        restBAccountMockMvc.perform(put("/api/b-accounts")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedBAccount)))
                .andExpect(status().isOk());

        // Validate the BAccount in the database
        List<BAccount> bAccounts = bAccountRepository.findAll();
        assertThat(bAccounts).hasSize(databaseSizeBeforeUpdate);
        BAccount testBAccount = bAccounts.get(bAccounts.size() - 1);
        assertThat(testBAccount.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testBAccount.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testBAccount.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    public void deleteBAccount() throws Exception {
        // Initialize the database
        bAccountRepository.saveAndFlush(bAccount);
        int databaseSizeBeforeDelete = bAccountRepository.findAll().size();

        // Get the bAccount
        restBAccountMockMvc.perform(delete("/api/b-accounts/{id}", bAccount.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<BAccount> bAccounts = bAccountRepository.findAll();
        assertThat(bAccounts).hasSize(databaseSizeBeforeDelete - 1);
    }
}
