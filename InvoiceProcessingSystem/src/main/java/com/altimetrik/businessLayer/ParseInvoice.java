package com.altimetrik.businessLayer;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;


public class ParseInvoice {

	private PDDocument doc;
	private PDFTextStripperByArea stripper;
	private PDFTextStripper pTextStripper;

	private String InvoiceNo;
	private String CustomerPO;
	private String InvoiceDate;
	private String address;
	private String totalDues;
	protected String [] arr = null;
	
	private File file;
	
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

	

	

	public ParseInvoice() {

		//this.file = GetInvoiceFromMail.targetFile;
		this.file=new File("D:\\Acushnet.pdf");
		try {

			this.doc = PDDocument.load(file);

		} catch (InvalidPasswordException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void fetchInvoiceData() throws IOException {

		stripper = new PDFTextStripperByArea();
		pTextStripper = new PDFTextStripper();

		try{
			doc = PDDocument.load(file);
			PDPage page = doc.getPage(0);
			
			PDFTextStripperByArea pdfStripper = new PDFTextStripperByArea();
			Rectangle2D.Float Rect1 = new Rectangle2D.Float(51,129,40,15);  
			Rectangle2D.Float Rect2 = new Rectangle2D.Float(180,129,45,15);
			Rectangle2D.Float Rect3 = new Rectangle2D.Float(290,147,55,15);
			Rectangle2D.Float Rect4 = new Rectangle2D.Float(51,169,200,45);
			Rectangle2D.Float Rect5 = new Rectangle2D.Float(51,312,1000,400);
	        pdfStripper.addRegion( "invoice No", Rect1 );    
	        pdfStripper.addRegion( "invoice Date", Rect2 );
	        pdfStripper.addRegion( "Customer PO", Rect3 );
	        pdfStripper.addRegion( "address", Rect4 );
	        pdfStripper.addRegion( "total invoice", Rect5 );
	        pdfStripper.extractRegions(page); 
			
	        this.InvoiceNo = pdfStripper.getTextForRegion("invoice No").trim();
	         
	        this.InvoiceDate = pdfStripper.getTextForRegion("invoice Date").trim();
	      
	        this.CustomerPO = pdfStripper.getTextForRegion("Customer PO").trim();
	      
	        this.address = pdfStripper.getTextForRegion("address").trim();
	       
	        
	        
	        this.totalDues = pdfStripper.getTextForRegion("total invoice").trim();
	        
	        arr = this.totalDues.split("\n");
	        String temp = arr[arr.length-1];
	        this.totalDues = "";
	        
//	
	        
	        for(int i=0;i<temp.length();i++){
	        	if(temp.charAt(i) == ',' || temp.charAt(i) == '$')	
	        		continue;
	        	this.totalDues += temp.charAt(i);
	        }
		}catch(IOException e){
			e.printStackTrace();
		}

	}
		

	@Override
	public String toString() {
		return "ParseInvoice [InvoiceNo=" + InvoiceNo + ", CustomerPO=" + CustomerPO + ", InvoiceDate=" + InvoiceDate
				+ ", address=" + address + ", totalDues=" + totalDues + "]";
	}

	

}
