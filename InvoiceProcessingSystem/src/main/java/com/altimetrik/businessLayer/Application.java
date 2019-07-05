package com.altimetrik.businessLayer;

public class Application {

	public static void main(String[] args) {
		//GetInvoiceFromMail getInvoiceFromMail=new GetInvoiceFromMail();
		//getInvoiceFromMail.recieveMailWithAttachment();
//		ParseInvoice parseInvoice=new ParseInvoice();
//		try {
//			parseInvoice.fetchInvoiceData();
//			System.out.println(parseInvoice.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		SendApprovalMail send=new SendApprovalMail();
		send.send("akshayagrawal87@gmail.com", "bandbaja","akshayagrawal87@gmail.com", "Testing", "bas yuhin");

	}

}
