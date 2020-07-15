package com.liufirst.dao;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import com.liufirst.model.Book;
import com.liufirst.model.Student;
import com.liufirst.util.StringUtil;

/**
 * ѧ�������ݿ����
 * @author 25833
 *
 */
public class StudentDao {
	/***
	 * ѧ����¼
	 * @param con
	 * @param stu
	 * @return
	 * @throws SQLException
	 */
	public static Student login(Connection con,Student stu)throws SQLException {
		Student student=null;
		String sql="select*from student where s_id=? and s_password=?";
		//�ǵ��׳��쳣
		PreparedStatement pstm=con.prepareStatement(sql);
		pstm.setString(1, stu.getS_id());
		pstm.setString(2, stu.getS_password());
		
		ResultSet rs=pstm.executeQuery();
		if(rs.next()) {
			student=new Student();//��ֹ��ָ���쳣
			/**
			 * student.setS_id(stu.getS_name());����
			 * ��Ϊ�����ѧ�������id��password�����ı����ȡ�ģ����Դ���login������student����û��name��Ϊnull
			 * ����Ӧ��ͨ��rs��ͨ�����ݿ������³�ʼ��
			 */
			student.setS_id(rs.getString("s_id"));
			student.setS_password(rs.getString("s_password"));
			student.setS_name(rs.getString("s_name"));
		}
		return student;
	}
	
	
	/**
	 * 
	 * �˴�����Ϊ���Ĺ���--ͼ�����  ��ѧ����ѯʹ��
	 * ѧ����ѯ
	 */
	/**
	public static ResultSet list(Connection con,Student  s)throws Exception {
		StringBuffer sb=new StringBuffer("select*from student");
		if(StringUtil.isNotEmpty(s.getS_name())) {//������ģ�������ʱisNOTempty������������
			sb.append(" and s_name like '%"+s.getS_name()+"%'");
		}
		PreparedStatement pstm=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstm.executeQuery();
	}
	**/
}
