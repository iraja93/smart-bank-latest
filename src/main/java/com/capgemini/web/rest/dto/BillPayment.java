package com.capgemini.web.rest.dto;

public class BillPayment {
	
	
	private long billId;
	private long billAmount;
	private long cardNo;
	
	public long getBillId() {
		return billId;
	}
	public void setBillId(long billId) {
		this.billId = billId;
	}
	public long getBillAmount() {
		return billAmount;
	}
	public long getCardNo() {
		return cardNo;
	}
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	public void setBillAmount(long billAmount) {
		this.billAmount = billAmount;
	}
		
	
	

}
