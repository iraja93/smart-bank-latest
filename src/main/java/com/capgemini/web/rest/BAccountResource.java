package com.capgemini.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.capgemini.domain.BAccount;
import com.capgemini.domain.Benificiary;

import com.capgemini.domain.Transaction;
import com.capgemini.domain.User;
import com.capgemini.repository.BAccountRepository;
import com.capgemini.repository.BenificiaryRepository;
import com.capgemini.repository.TransactionRepository;
import com.capgemini.repository.UserRepository;
import com.capgemini.web.rest.dto.AccountNo;
import com.capgemini.web.rest.dto.FundTransfer;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BAccount.
 */
@RestController
@RequestMapping("/api")
public class BAccountResource {

    private final Logger log = LoggerFactory.getLogger(BAccountResource.class);
        
    @Inject
    private BAccountRepository bAccountRepository;
    @Inject
    private BenificiaryRepository benificiaryRepository;
    @Inject
    private TransactionRepository transactionRepository;
    @Inject
    private UserRepository userRepository;
    
    /**
     * POST  /b-accounts : Create a new bAccount.
     *
     * @param bAccount the bAccount to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bAccount, or with status 400 (Bad Request) if the bAccount has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/b-accounts",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BAccount> createBAccount(@RequestBody BAccount bAccount) throws URISyntaxException {
        log.debug("REST request to save BAccount : {}", bAccount);
        if (bAccount.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("bAccount", "idexists", "A new bAccount cannot already have an ID")).body(null);
        }
        BAccount result = bAccountRepository.save(bAccount);
        return ResponseEntity.created(new URI("/api/b-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("bAccount", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /b-accounts : Updates an existing bAccount.
     *
     * @param bAccount the bAccount to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bAccount,
     * or with status 400 (Bad Request) if the bAccount is not valid,
     * or with status 500 (Internal Server Error) if the bAccount couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/b-accounts",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BAccount> updateBAccount(@RequestBody BAccount bAccount) throws URISyntaxException {
        log.debug("REST request to update BAccount : {}", bAccount);
        if (bAccount.getId() == null) {
            return createBAccount(bAccount);
        }
        BAccount result = bAccountRepository.save(bAccount);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("bAccount", bAccount.getId().toString()))
            .body(result);
    }

    /**
     * GET  /b-accounts : get all the bAccounts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bAccounts in body
     */
    @RequestMapping(value = "/b-accounts",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<BAccount> getAllBAccounts() {
        log.debug("REST request to get all BAccounts");
        List<BAccount> bAccounts = bAccountRepository.findAll();
        return bAccounts;
    }

    /**
     * GET  /b-accounts/:id : get the "id" bAccount.
     *
     * @param id the id of the bAccount to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bAccount, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/b-accounts/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BAccount> getBAccount(@PathVariable Long id) {
        log.debug("REST request to get BAccount : {}", id);
        BAccount bAccount = bAccountRepository.findOne(id);
        return Optional.ofNullable(bAccount)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /b-accounts/:id : delete the "id" bAccount.
     *
     * @param id the id of the bAccount to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/b-accounts/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteBAccount(@PathVariable Long id) {
        log.debug("REST request to delete BAccount : {}", id);
        bAccountRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("bAccount", id.toString())).build();
    }
    
    @RequestMapping(value ="/fundTransfer",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<?> fundTransfer(@RequestBody FundTransfer fundTransfer) throws URISyntaxException {

    	//List<Benificiary> payeeList=benificiaryRepository.findByAccountIDAndBAccountId(fundTransfer.getSenderId(),fundTransfer.getReceiverId());
    	/*List<Benificiary> payeeList=null;
    	if(payeeList.size()==0)
		{
			return new ResponseEntity<BAccount>(HttpStatus.BAD_REQUEST);
		}*/
		BAccount senderAccount = bAccountRepository.findOne(fundTransfer.getSenderId());
		BAccount receiverAccount = bAccountRepository.findOne(fundTransfer.getReceiverId());

		senderAccount.setBalance(senderAccount.getBalance()-fundTransfer.getAmount());	
		if(senderAccount.getBalance()<fundTransfer.getAmount()||senderAccount.getBalance()<=0 || fundTransfer.getAmount()<=0)
		{
			return new ResponseEntity<String>("Insufficient Balaance",HttpStatus.BAD_REQUEST);
			
			
		}

		LocalDate today = LocalDate.now();
		transactionRepository.save(new Transaction(today,"Debited $"+fundTransfer.getAmount()+"- Transferred To Account No "+senderAccount.getId(),fundTransfer.getAmount(),senderAccount));

		receiverAccount.setBalance(receiverAccount.getBalance()+fundTransfer.getAmount());

		transactionRepository.save(new Transaction(today,"Credited $"+fundTransfer.getAmount()+"- Transferred To Account No "+senderAccount.getId(),fundTransfer.getAmount(),receiverAccount));
		bAccountRepository.save(senderAccount);
		bAccountRepository.save(receiverAccount);
		return ResponseEntity.created(new URI("/api//" + fundTransfer.getSenderId()))
				.headers(HeaderUtil.createEntityCreationAlert("Fund Transfer Done", senderAccount.getId().toString()))
				.body(senderAccount);
	}
    
    @RequestMapping(value ="/accountId",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<BAccount> getAccountId() throws URISyntaxException {

    	//List<Benificiary> payeeList=benificiaryRepository.findByAccountIDAndBAccountId(fundTransfer.getSenderId(),fundTransfer.getReceiverId());
    	/*List<Benificiary> payeeList=null;
    	if(payeeList.size()==0)
		{
			return new ResponseEntity<BAccount>(HttpStatus.BAD_REQUEST);
		}*/
    	
    	//Optional<User> u=userRepository.findOneByLogin(logIn);
    	
    	//AccountNo a=new AccountNo();
    	List<BAccount> ba=bAccountRepository.findByUserIsCurrentUser();
    	
    	return ba;
	}
    
    

}
