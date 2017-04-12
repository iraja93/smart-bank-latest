package com.capgemini.web.rest;

import com.capgemini.SmartbankApp;
import com.capgemini.domain.Benificiary;
import com.capgemini.repository.BenificiaryRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the BenificiaryResource REST controller.
 *
 * @see BenificiaryResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SmartbankApp.class)
@WebAppConfiguration
@IntegrationTest
public class BenificiaryResourceIntTest {

    private static final long DEFAULT_ACCOUNT_ID = 1;
    private static final long UPDATED_ACCOUNT_ID = 2;
    private static final String DEFAULT_DISPLAY_NAME = "AAAAA";
    private static final String UPDATED_DISPLAY_NAME = "BBBBB";

    @Inject
    private BenificiaryRepository benificiaryRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBenificiaryMockMvc;

    private Benificiary benificiary;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BenificiaryResource benificiaryResource = new BenificiaryResource();
        ReflectionTestUtils.setField(benificiaryResource, "benificiaryRepository", benificiaryRepository);
        this.restBenificiaryMockMvc = MockMvcBuilders.standaloneSetup(benificiaryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        benificiary = new Benificiary();
        benificiary.setAccountId(DEFAULT_ACCOUNT_ID);
        benificiary.setDisplayName(DEFAULT_DISPLAY_NAME);
    }

    @Test
    @Transactional
    public void createBenificiary() throws Exception {
        int databaseSizeBeforeCreate = benificiaryRepository.findAll().size();

        // Create the Benificiary

        restBenificiaryMockMvc.perform(post("/api/benificiaries")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(benificiary)))
                .andExpect(status().isCreated());

        // Validate the Benificiary in the database
        List<Benificiary> benificiaries = benificiaryRepository.findAll();
        assertThat(benificiaries).hasSize(databaseSizeBeforeCreate + 1);
        Benificiary testBenificiary = benificiaries.get(benificiaries.size() - 1);
        assertThat(testBenificiary.getAccountId()).isEqualTo(DEFAULT_ACCOUNT_ID);
        assertThat(testBenificiary.getDisplayName()).isEqualTo(DEFAULT_DISPLAY_NAME);
    }

    @Test
    @Transactional
    public void getAllBenificiaries() throws Exception {
        // Initialize the database
        benificiaryRepository.saveAndFlush(benificiary);

        // Get all the benificiaries
        restBenificiaryMockMvc.perform(get("/api/benificiaries?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(benificiary.getId().intValue())))
               
                .andExpect(jsonPath("$.[*].displayName").value(hasItem(DEFAULT_DISPLAY_NAME.toString())));
    }

    @Test
    @Transactional
    public void getBenificiary() throws Exception {
        // Initialize the database
        benificiaryRepository.saveAndFlush(benificiary);

        // Get the benificiary
        restBenificiaryMockMvc.perform(get("/api/benificiaries/{id}", benificiary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(benificiary.getId().intValue()))
            
            .andExpect(jsonPath("$.displayName").value(DEFAULT_DISPLAY_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBenificiary() throws Exception {
        // Get the benificiary
        restBenificiaryMockMvc.perform(get("/api/benificiaries/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBenificiary() throws Exception {
        // Initialize the database
        benificiaryRepository.saveAndFlush(benificiary);
        int databaseSizeBeforeUpdate = benificiaryRepository.findAll().size();

        // Update the benificiary
        Benificiary updatedBenificiary = new Benificiary();
        updatedBenificiary.setId(benificiary.getId());
        updatedBenificiary.setAccountId(UPDATED_ACCOUNT_ID);
        updatedBenificiary.setDisplayName(UPDATED_DISPLAY_NAME);

        restBenificiaryMockMvc.perform(put("/api/benificiaries")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedBenificiary)))
                .andExpect(status().isOk());

        // Validate the Benificiary in the database
        List<Benificiary> benificiaries = benificiaryRepository.findAll();
        assertThat(benificiaries).hasSize(databaseSizeBeforeUpdate);
        Benificiary testBenificiary = benificiaries.get(benificiaries.size() - 1);
        assertThat(testBenificiary.getAccountId()).isEqualTo(UPDATED_ACCOUNT_ID);
        assertThat(testBenificiary.getDisplayName()).isEqualTo(UPDATED_DISPLAY_NAME);
    }

    @Test
    @Transactional
    public void deleteBenificiary() throws Exception {
        // Initialize the database
        benificiaryRepository.saveAndFlush(benificiary);
        int databaseSizeBeforeDelete = benificiaryRepository.findAll().size();

        // Get the benificiary
        restBenificiaryMockMvc.perform(delete("/api/benificiaries/{id}", benificiary.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Benificiary> benificiaries = benificiaryRepository.findAll();
        assertThat(benificiaries).hasSize(databaseSizeBeforeDelete - 1);
    }
}
