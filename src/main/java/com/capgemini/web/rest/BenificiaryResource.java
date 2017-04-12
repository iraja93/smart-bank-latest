package com.capgemini.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.capgemini.domain.BAccount;
import com.capgemini.domain.Benificiary;
import com.capgemini.repository.BenificiaryRepository;
import com.capgemini.web.rest.dto.Payee;
import com.capgemini.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Benificiary.
 */
@RestController
@RequestMapping("/api")
public class BenificiaryResource {

    private final Logger log = LoggerFactory.getLogger(BenificiaryResource.class);
        
    @Inject
    private BenificiaryRepository benificiaryRepository;
    
    /**
     * POST  /benificiaries : Create a new benificiary.
     *
     * @param benificiary the benificiary to create
     * @return the ResponseEntity with status 201 (Created) and with body the new benificiary, or with status 400 (Bad Request) if the benificiary has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/addBenificiaries",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Benificiary> createBenificiary(@RequestBody Payee payee) throws URISyntaxException {
        log.debug("REST request to save Benificiary : {}", payee);
        /*if (benificiary.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("benificiary", "idexists", "A new benificiary cannot already have an ID")).body(null);
        }*/
        Benificiary benificiary=new Benificiary();
        benificiary.setAccountId(payee.getAccountId());
        benificiary.setDisplayName(payee.getDisplayName());
        BAccount bAccount=new BAccount();
        bAccount.setId(payee.getbAccountId());
        benificiary.setBAccount(bAccount);
        
        Benificiary result = benificiaryRepository.save(benificiary);
        return ResponseEntity.created(new URI("/api/benificiaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("benificiary", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /benificiaries : Updates an existing benificiary.
     *
     * @param benificiary the benificiary to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated benificiary,
     * or with status 400 (Bad Request) if the benificiary is not valid,
     * or with status 500 (Internal Server Error) if the benificiary couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/benificiaries",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Benificiary> updateBenificiary(@RequestBody Benificiary benificiary) throws URISyntaxException {
        log.debug("REST request to update Benificiary : {}", benificiary);
        /*if (benificiary.getId() == null) {
            return createBenificiary(benificiary);
        }*/
        Benificiary result = benificiaryRepository.save(benificiary);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("benificiary", benificiary.getId().toString()))
            .body(result);
    }

    /**
     * GET  /benificiaries : get all the benificiaries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of benificiaries in body
     */
    @RequestMapping(value = "/getAllBenificiaries",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Benificiary> getAllBenificiaries() {
        log.debug("REST request to get all Benificiaries");
        List<Benificiary> benificiaries = benificiaryRepository.findAll();
        return benificiaries;
    }

    /**
     * GET  /benificiaries/:id : get the "id" benificiary.
     *
     * @param id the id of the benificiary to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the benificiary, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/benificiaries/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Benificiary> getBenificiary(@PathVariable Long id) {
        log.debug("REST request to get Benificiary : {}", id);
        Benificiary benificiary = benificiaryRepository.findOne(id);
        return Optional.ofNullable(benificiary)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /benificiaries/:id : delete the "id" benificiary.
     *
     * @param id the id of the benificiary to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/deleteBenificiaries/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteBenificiary(@PathVariable Long id) {
        log.debug("REST request to delete Benificiary : {}", id);
        benificiaryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("benificiary", id.toString())).build();
    }
    
    @RequestMapping(value = "/getBenificiariesByAccountId/{accountId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @Timed
        public List<Benificiary> getBenificiaryByAccountId(@PathVariable Long accountId) {
            log.debug("REST request to get Benificiary : {}", accountId);
            List<Benificiary> benificiary = benificiaryRepository.findOneByBAccountId(accountId);
            return benificiary;
        }

}
