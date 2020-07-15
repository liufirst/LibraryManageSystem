package com.liufirst.model;
/**
 * 管理员实体
 * @author 25833
 *
 */
public class Admin {
	private String a_id;
	private String a_name;
	private String a_password;
	//登录用id和密码
	public String getA_id() {
		return a_id;
	}
	public void setA_id(String a_id) {
		this.a_id = a_id;
	}
	public String getA_password() {
		return a_password;
	}
	public void setA_password(String a_password) {
		this.a_password = a_password;
	}
	public String getA_name() {
		return a_name;
	}
	public void setA_name(String a_name) {
		this.a_name = a_name;
	}
	//构造方法
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	public Admin(String a_id, String a_password) {
		super();
		this.a_id = a_id;
		this.a_password = a_password;
	}
	

}
