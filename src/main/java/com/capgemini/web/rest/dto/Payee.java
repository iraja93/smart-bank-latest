package com.capgemini.web.rest.dto;

public class Payee {
private long accountId;
private String ifscCode;
private long bAccountId;
private String displayName;
public long getAccountId() {
	return accountId;
}
public void setAccountId(long accountId) {
	this.accountId = accountId;
}
public String getIfscCode() {
	return ifscCode;
}
public void setIfscCode(String ifscCode) {
	this.ifscCode = ifscCode;
}
public long getbAccountId() {
	return bAccountId;
}
public void setbAccountId(long bAccountId) {
	this.bAccountId = bAccountId;
}
public String getDisplayName() {
	return displayName;
}
public void setDisplayName(String displayName) {
	this.displayName = displayName;
}

}
