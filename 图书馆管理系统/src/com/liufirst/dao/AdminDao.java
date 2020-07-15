package com.liufirst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.liufirst.model.Admin;


/**
 * 管理员与数据库的操作
 * @author 25833
 *
 */
public class AdminDao {
	/**
	 * 管理员登陆
	 * @param con
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	public static Admin login(Connection con,Admin admin)throws Exception {
		Admin administer=null;
		
		String sql="select *from admin where a_id=? and a_password=?";
		
		PreparedStatement pstmt=con.prepareStatement(sql);
		
		pstmt.setString(1, admin.getA_id());
		pstmt.setString(2, admin.getA_password());
		
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			administer=new Admin();//防止空指针异常
			//resultUser.setUserId(rs.getInt("userId"));靠，这是个bug，不应该写上。t_user表中没有userId字段
			administer.setA_id((rs.getString("a_id")));
			administer.setA_password((rs.getString("a_password")));
			administer.setA_name(rs.getString("a_name"));
		}
		 
		return administer;
	}


}
