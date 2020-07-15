package com.liufirst.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.liufirst.model.Status;
import com.liufirst.model.Student;
import com.liufirst.util.StringUtil;

/**
 * 图书状态与数据库操作
 * @author 25833
 *
 */
public class StatusDao {
	/**
	 * 图书状态查询
	 * @param con
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static ResultSet list(Connection con,Status st)throws Exception {
		
		String b=new Integer(st.getStatus_id()).toString();
		StringBuffer sb=new StringBuffer("select*from status");
		if(StringUtil.isNotEmpty(b) ){//操他妈的！！这里时isNOTempty！！！啊啊啊
			sb.append(" and status_id like '%"+st.getStatus_id()+"%'");
		}if(StringUtil.isNotEmpty(st.getStatus_name())) {
			sb.append(" and status_name like '%"+st.getStatus_name()+"%'");
		}
		PreparedStatement pstm=con.prepareStatement(sb.toString().replaceFirst("and", "where"));//
		return pstm.executeQuery();
	}

}
