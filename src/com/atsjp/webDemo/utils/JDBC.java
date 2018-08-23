package com.atsjp.webDemo.utils;

import javax.servlet.annotation.WebServlet;
import java.sql.*;

/*
 * 
 * ��ú��ͷ�Connection����
 */
@WebServlet("/ServletConfigurator")
public class JDBC {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String PROXOOL_DRIVER = "org.logicalcobwebs.proxool.ProxoolDriver";
	private static final String PROXOOL_POOL = "proxool.crm";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/web_book?autoReconnect=true";
	// Database credentials -- ���ݿ����������Լ��޸�
	private static final String USER = "root";
	private static final String PASS = "root";

	/*
	 * 
	 * ���Connection���Ӷ���
	 */
	public static synchronized Connection getConnection() {
        Connection conn = null;
	    try {
//			Class.forName(JDBC_DRIVER);
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // ����proxool���ӳأ����tomcat�����ݿ�������ӹ�������
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
	 * �ͷ�Connection���Ӷ���
	 */
//	public static boolean closeConnection() {
//		try {
//			if (conn != null) {
//				conn.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}

    /**
     * �ر�����
     *
     * @param conn
     * @param pstmt
     * @param rs
     */
    public static void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
