package com.capgemini.web.rest.dto;

import java.util.List;

public class AccountNo {
	private long userNo;
	private List<Long> accNo;
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public List<Long> getAccNo() {
		return accNo;
	}
	public void setAccNo(List<Long> accNo) {
		this.accNo = accNo;
	}
	

}
