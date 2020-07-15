package com.liufirst.model;

import java.util.Date;

public class Borrow {
	private int bo_id;
	private int bo_bookid;
	private String bo_studentid;
	
	private String bo_bookname;//只提供给归还图书，查询时使用
	
	
	private Date time;
	
	public Borrow() {
		super();
	}
	
	public Borrow(int bo_bookid, String bo_studentid) {
		super();
		this.bo_bookid = bo_bookid;
		this.bo_studentid = bo_studentid;
	}

	public Borrow(int bo_id, int bo_bookid, String bo_studentid) {//等待以后升级，加上日期
		super();
		this.bo_id = bo_id;
		this.bo_bookid = bo_bookid;
		this.bo_studentid = bo_studentid;
	}
	
	
	public Borrow(String bo_studentid, String bo_bookname) {//只提供给归还图书，查询时使用
		super();
		this.bo_studentid = bo_studentid;
		this.bo_bookname = bo_bookname;
	}

	public int getBo_id() {
		return bo_id;
	}
	public void setBo_id(int bo_id) {
		this.bo_id = bo_id;
	}
	public int getBo_bookid() {
		return bo_bookid;
	}
	public void setBo_bookid(int bo_bookid) {
		this.bo_bookid = bo_bookid;
	}
	public String getBo_studentid() {
		return bo_studentid;
	}
	public void setBo_studentid(String bo_studentid) {
		this.bo_studentid = bo_studentid;
	}

	public String getBo_bookname() {
		return bo_bookname;
	}

	public void setBo_bookname(String bo_bookname) {
		this.bo_bookname = bo_bookname;
	}
	
	
	

}
