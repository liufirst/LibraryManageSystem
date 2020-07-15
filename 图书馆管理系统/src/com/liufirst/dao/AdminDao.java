package com.liufirst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.liufirst.model.Admin;


/**
 * ����Ա�����ݿ�Ĳ���
 * @author 25833
 *
 */
public class AdminDao {
	/**
	 * ����Ա��½
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
			administer=new Admin();//��ֹ��ָ���쳣
			//resultUser.setUserId(rs.getInt("userId"));�������Ǹ�bug����Ӧ��д�ϡ�t_user����û��userId�ֶ�
			administer.setA_id((rs.getString("a_id")));
			administer.setA_password((rs.getString("a_password")));
			administer.setA_name(rs.getString("a_name"));
		}
		 
		return administer;
	}


}
