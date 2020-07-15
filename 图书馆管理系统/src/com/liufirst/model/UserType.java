package com.liufirst.model;
/**
 * 枚举类
 * 登陆界面的权限选项，下拉框
 * @author 25833
 *
 */
public enum UserType {
	ADMIN("管理员",0),STUDENT("学生",1);//("  ",  999 )
	
	private String name;
	private int index;//
	

	
	private UserType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	//重写
	public String toString() {
		return this.name;
	}
	
	
	
}
