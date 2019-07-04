package com.altimetrik.businessLayer;

public class Application {

	public static void main(String[] args) {
		GetInvoiceFromMail getInvoiceFromMail=new GetInvoiceFromMail();
		getInvoiceFromMail.recieveMailWithAttachment();
		

	}

}
