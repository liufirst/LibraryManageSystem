package com.liufirst.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


/**
 * 数据库工具类
 * @author 25833
 *
 */
public class DBUtil {
	private static final String url="jdbc:mysql://localhost:3306/libraryManage";//连接到libraryManage数据库
	private static final String dbName="root";
	private static final String dbPassword="root";
	//驱动名
	private static final String driver="com.mysql.jdbc.Driver";
	/**
	 * 创建数据库连接
	 * @return
	 */
	public static Connection createConnection() {
		Connection con=null;
		
		try {
			Class.forName(driver);
			try {
				con=DriverManager.getConnection(url,dbName,dbPassword);
				//JOptionPane.showMessageDialog(null, "数据库连接成功");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "数据库连接失败！");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog( null, "数据库驱动加载失败！");
		}
		
		return con;
	}
	/**
	 * 关闭数据库连接
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