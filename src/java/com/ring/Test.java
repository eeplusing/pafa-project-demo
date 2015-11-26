package com.ring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Test {
	public static void main(String[] args) {

		try {

//			Context initCtx = new InitialContext();
//			DataSource ds = (DataSource) initCtx
//					.lookup("java:comp/env/jdbc/ringwebDS");
//			Connection conn = ds.getConnection();
			System.out.println("你好!");
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}