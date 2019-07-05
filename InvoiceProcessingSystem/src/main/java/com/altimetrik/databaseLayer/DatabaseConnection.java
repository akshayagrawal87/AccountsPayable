package com.altimetrik.databaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class DatabaseConnection {
	private String userName;
	private String password;
	private String url;

	protected static Connection conn;
	protected static Properties connectionProps;
	protected static Statement statement;

	public DatabaseConnection(String userName, String password, String url) {

		super();
		Locale locale = Locale.getDefault();
		String baseName = "databaseConfig";

		ResourceBundle res = ResourceBundle.getBundle(baseName, locale);

		this.userName = res.getString(userName);
		this.password = res.getString(password);
		this.url = res.getString(url);

	}

	public void connectToDatabase() throws SQLException {
		connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);
		conn = DriverManager.getConnection(url, connectionProps);
		System.out.println("Database connection successful");
		statement = conn.createStatement();

	}

}
