package com.liufirst.model;
/**
 * 学生实体
 * @author 25833
 *
 */
public class Student {
	//private int sql_id;//数据库
	private String s_id;
	private String s_name;
	private String s_password;
	private String s_sex;
	//构造方法
	public Student() {
		
	}
	public Student(String s_id, String s_password) {
		super();
		this.s_id = s_id;
		this.s_password = s_password;
	}
	

	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public String getS_password() {
		return s_password;
	}
	public void setS_password(String s_password) {
		this.s_password = s_password;
	}
	public String getS_sex() {
		return s_sex;
	}
	public void setS_sex(String s_sex) {
		this.s_sex = s_sex;
	}
	
	

}
