package com.liufirst.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


/**
 * ���ݿ⹤����
 * @author 25833
 *
 */
public class DBUtil {
	private static final String url="jdbc:mysql://localhost:3306/libraryManage";//���ӵ�libraryManage���ݿ�
	private static final String dbName="root";
	private static final String dbPassword="root";
	//������
	private static final String driver="com.mysql.jdbc.Driver";
	/**
	 * �������ݿ�����
	 * @return
	 */
	public static Connection createConnection() {
		Connection con=null;
		
		try {
			Class.forName(driver);
			try {
				con=DriverManager.getConnection(url,dbName,dbPassword);
				//JOptionPane.showMessageDialog(null, "���ݿ����ӳɹ�");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "���ݿ�����ʧ�ܣ�");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, "���ݿ���������ʧ�ܣ�");
		}
		
		return con;
	}
	/**
	 * �ر����ݿ�����
	 * @param con
	 */
	public static void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}