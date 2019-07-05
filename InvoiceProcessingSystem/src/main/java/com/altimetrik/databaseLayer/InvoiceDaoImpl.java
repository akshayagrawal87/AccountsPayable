package com.altimetrik.databaseLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.altimetrik.businessLayer.ExchangeInvoiceData;
import com.altimetrik.businessLayer.ParseInvoice;

public class InvoiceDaoImpl implements InvoiceDao {

	public void insertInvoice(List<Invoice> Invoice) {

		ExchangeInvoiceData exchangeInvoiceData = new ParseInvoice();
		
		List<String> list = exchangeInvoiceData.getInvoiceData();
		
		DatabaseConnection databaseConnection=new DatabaseConnection();
		
		try {
			
			databaseConnection.connectToDatabase();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try{
		
		String SQL = "INSERT INTO AccountsPayableunApproved " + "VALUES(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = DatabaseConnection.conn.prepareStatement(SQL);
		pstmt.setLong(1, Long.parseLong(list.get(1)));
		pstmt.setString(2, list.get(2));
		pstmt.setString(3, list.get(3));
		pstmt.setString(4, list.get(3));
		pstmt.setString(5, list.get(4));
		pstmt.executeUpdate();
		}
		catch(SQLException e){
			
		}

	}

	public void getAllInvoice() {
		try{
		ResultSet resultSet = DatabaseConnection.statement.executeQuery("select * from AccountsPayableunApproved");
		
		if (resultSet.next()) {
			System.out.println("Invoice No: " + resultSet.getLong(1) + "\n" + "Invoice Date: " + resultSet.getString(2)
					+ "\n" + "Customer P.O: " + resultSet.getString(3) + "\n" + "Address: " + resultSet.getString(4)
					+ "\n" + "Total Due Amount: " + resultSet.getDouble(5) + "\n");
		} else {
			System.out.println("Fail !!");
		}
		}
		catch(SQLException e){
			
		}
	}

	

}
