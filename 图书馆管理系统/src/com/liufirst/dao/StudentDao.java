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
 * 学生与数据库操作
 * @author 25833
 *
 */
public class StudentDao {
	/***
	 * 学生登录
	 * @param con
	 * @param stu
	 * @return
	 * @throws SQLException
	 */
	public static Student login(Connection con,Student stu)throws SQLException {
		Student student=null;
		String sql="select*from student where s_id=? and s_password=?";
		//记得抛出异常
		PreparedStatement pstm=con.prepareStatement(sql);
		pstm.setString(1, stu.getS_id());
		pstm.setString(2, stu.getS_password());
		
		ResultSet rs=pstm.executeQuery();
		if(rs.next()) {
			student=new Student();//防止空指针异常
			/**
			 * student.setS_id(stu.getS_name());不行
			 * 因为传入的学生对象的id和password都是文本框获取的，所以传入login方法的student对象没有name，为null
			 * 所以应该通过rs来通过数据库来重新初始化
			 */
			student.setS_id(rs.getString("s_id"));
			student.setS_password(rs.getString("s_password"));
			student.setS_name(rs.getString("s_name"));
		}
		return student;
	}
	
	
	/**
	 * 
	 * 此处不能为借阅管理--图书借阅  的学生查询使用
	 * 学生查询
	 */
	/**
	public static ResultSet list(Connection con,Student  s)throws Exception {
		StringBuffer sb=new StringBuffer("select*from student");
		if(StringUtil.isNotEmpty(s.getS_name())) {//操他妈的！！这里时isNOTempty！！！啊啊啊
			sb.append(" and s_name like '%"+s.getS_name()+"%'");
		}
		PreparedStatement pstm=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstm.executeQuery();
	}
	**/
}
