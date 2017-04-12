package com.capgemini.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.capgemini.domain.BAccount;
import com.capgemini.domain.Bill;
import com.capgemini.domain.Card;
import com.capgemini.domain.Transaction;
import com.capgemini.repository.BAccountRepository;
import com.capgemini.repository.BillRepository;
import com.capgemini.repository.CardRepository;
import com.capgemini.repository.TransactionRepository;
import com.capgemini.web.rest.dto.BillPayment;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Bill.
 */
@RestController
@RequestMapping("/api")
public class BillResource {

    private final Logger log = LoggerFactory.getLogger(BillResource.class);
        
    @Inject
    private BillRepository billRepository;
    
    @Inject
    private BAccountRepository bAccountRepository;
    @Inject
    private TransactionRepository transactionRepository;
    @Inject
    private CardRepository cardRepository;
    
    
    /**
     * POST  /bills : Create a new bill.
     *
     * @param bill the bill to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bill, or with status 400 (Bad Request) if the bill has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/bills",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) throws URISyntaxException {
        log.debug("REST request to save Bill : {}", bill);
        if (bill.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("bill", "idexists", "A new bill cannot already have an ID")).body(null);
        }
        Bill result = billRepository.save(bill);
        return ResponseEntity.created(new URI("/api/bills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("bill", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bills : Updates an existing bill.
     *
     * @param bill the bill to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bill,
     * or with status 400 (Bad Request) if the bill is not valid,
     * or with status 500 (Internal Server Error) if the bill couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/bills",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Bill> updateBill(@RequestBody Bill bill) throws URISyntaxException {
        log.debug("REST request to update Bill : {}", bill);
        if (bill.getId() == null) {
            return createBill(bill);
        }
        Bill result = billRepository.save(bill);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("bill", bill.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bills : get all the bills.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bills in body
     */
    @RequestMapping(value = "/bills",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Bill> getAllBills() {
        log.debug("REST request to get all Bills");
        List<Bill> bills = billRepository.findAll();
        return bills;
    }

    /**
     * GET  /bills/:id : get the "id" bill.
     *
     * @param id the id of the bill to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bill, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/bills/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Bill> getBill(@PathVariable Long id) {
        log.debug("REST request to get Bill : {}", id);
        Bill bill = billRepository.findOne(id);
        return Optional.ofNullable(bill)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /bills/:id : delete the "id" bill.
     *
     * @param id the id of the bill to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/bills/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        log.debug("REST request to delete Bill : {}", id);
        billRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("bill", id.toString())).build();
    }
    
    @RequestMapping(value ="/payPendingBills",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Bill> billTransfer(@RequestBody BillPayment billPayment) throws URISyntaxException{

    	List<Card> c=cardRepository.findOneByBAccountId(billPayment.getCardNo());
		BAccount ba=new BAccount();
		ba.setId(c.get(0).getUser().getId());
    	BAccount senderAccount=bAccountRepository.findOne(ba.getId());
		/*if(billPayment.getBillAmount()>senderAccount.getBalance())
		{
			return new ResponseEntity<Bill>(HttpStatus.BAD_REQUEST);
		}
*/
		senderAccount.setBalance(senderAccount.getBalance()-billPayment.getBillAmount());
		bAccountRepository.save(senderAccount);
		LocalDate today = LocalDate.now();
		transactionRepository.save(new Transaction(today,"Debit- For Bill Payment",billPayment.getBillAmount(),senderAccount));
		Bill b=billRepository.findOne(billPayment.getBillId());
		billRepository.delete(b.getId());
		return ResponseEntity.ok(b);
	}
    
    @RequestMapping(value = "/getBillsDetailsByAccountNo/{accountNo}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @Timed
        public List<Bill> getBillDetailsByCardNo(@PathVariable Long accountNo) {
            log.debug("REST request to get Bill : {}", accountNo);
            List<Card> cards=cardRepository.findOneByBAccountId(accountNo);
            List<Bill> bills=new ArrayList<Bill>();
            for(Card c : cards)
            {
            	List<Bill> b = billRepository.findOneByCardId(c.getId());
            	bills.addAll(b);
            }
            //List<Bill> bills = billRepository.findOneByCardId(cardNo);
            return bills;
        }
    

}
