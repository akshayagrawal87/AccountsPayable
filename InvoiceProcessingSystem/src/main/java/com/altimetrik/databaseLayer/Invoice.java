package com.altimetrik.databaseLayer;

public class Invoice {
	private String InvoiceNo;
	private String CustomerPO;
	private String InvoiceDate;
	private String address;
	private String totalDues;
	
	
	public String getInvoiceNo() {
		return InvoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		InvoiceNo = invoiceNo;
	}
	public String getCustomerPO() {
		return CustomerPO;
	}
	public void setCustomerPO(String customerPO) {
		CustomerPO = customerPO;
	}
	public String getInvoiceDate() {
		return InvoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		InvoiceDate = invoiceDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTotalDues() {
		return totalDues;
	}
	public void setTotalDues(String totalDues) {
		this.totalDues = totalDues;
	}
	

}
