package com.atsjp.webDemo.utils;

import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * 
 * 获得和释放Connection对象
 */
@WebServlet("/ServletConfigurator")
public class JDBC {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String PROXOOL_DRIVER = "org.logicalcobwebs.proxool.ProxoolDriver";
	private static final String PROXOOL_POOL = "proxool.crm";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/web_book?autoReconnect=true";
	// Database credentials -- 数据库名和密码自己修改
	private static final String USER = "root";
	private static final String PASS = "root";
	private static Connection conn;

	/*
	 * 
	 * 获得Connection连接对象
	 */
	public static Connection getConnection() {
		try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 改用proxool连接池，解决tomcat与数据库空闲连接过期问题
			Class.forName(PROXOOL_DRIVER);
			conn = DriverManager.getConnection(PROXOOL_POOL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * 
	 * 释放Connection连接对象
	 */
	public static boolean closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
