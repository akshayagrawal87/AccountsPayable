package com.altimetrik.businessLayer;

import java.io.IOException;

public class Application {

	public static void main(String[] args) {
		//GetInvoiceFromMail getInvoiceFromMail=new GetInvoiceFromMail();
		//getInvoiceFromMail.recieveMailWithAttachment();
		ParseInvoice parseInvoice=new ParseInvoice();
		try {
			parseInvoice.fetchInvoiceData();
			System.out.println(parseInvoice.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
