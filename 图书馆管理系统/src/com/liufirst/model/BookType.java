package com.liufirst.model;
/**
 * 图书类别实体
 * @author 25833
 *
 */
public class BookType {
	private int bt_id;
	private String bt_name;
	private String bt_description;//类别描述
	
	public BookType(int bt_id, String bt_name, String bt_description) {
		super();
		this.bt_id = bt_id;
		this.bt_name = bt_name;
		this.bt_description = bt_description;
	}
	public BookType(String bt_name, String bt_description) {
		super();
		this.bt_name = bt_name;
		this.bt_description = bt_description;
	}
	public BookType() {
		super();
	}
	public String getBt_name() {
		return bt_name;
	}
	public void setBt_name(String bt_name) {
		this.bt_name = bt_name;
	}
	public String getBt_description() {
		return bt_description;
	}
	public void setBt_description(String bt_description) {
		this.bt_description = bt_description;
	}
	public int getBt_id() {
		return bt_id;
	}
	public void setBt_id(int bt_id) {
		this.bt_id = bt_id;
	}
	@Override
	public String toString() {
		return bt_name;
	}
	

	
	
}
