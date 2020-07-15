package com.liufirst.model;

public class Book {
	//private int book_id;//数据库里的编号顺序
	private int b_id;//数据库里的编号
	private String b_name;
	private String b_writer;
	private String b_publisher;//出版商
	private Integer kind_id;//类别id，与BookType关联
	
	private String b_status_id;
	
	private String b_description; 
	/**
	 * 2020.7.9
	 * 其是不太对，应该重新修改，增加图书状态
	 * 在添加图书frm设置类别为下拉框
	 */
	//构造方法
	public Book() {
		super();
	}
	
	public Book(String b_name, String b_writer, String b_publisher, Integer kind_id, String b_description) {
		super();
		this.b_name = b_name;
		this.b_writer = b_writer;
		this.b_publisher = b_publisher;
		this.kind_id = kind_id;
		this.b_description = b_description;
	}

	
	
	
	
	
	//是为了 BorrowManageInterFrm的借阅-确认方法，更改图书的状态（借阅）
	public Book(int b_id) {
		super();
		this.b_id = b_id;
	}

	public Book(String b_name, String b_writer) {
		super();
		this.b_name = b_name;
		this.b_writer = b_writer;
	}

	public Book(int b_id, String b_name, String b_writer, String b_publisher, Integer kind_id, String b_description) {
		super();
		this.b_id = b_id;
		this.b_name = b_name;
		this.b_writer = b_writer;
		this.b_publisher = b_publisher;
		this.kind_id = kind_id;
		this.b_description = b_description;
	}

	public Book(String b_name, String b_writer, Integer kind_id) {
		super();
		this.b_name = b_name;
		this.b_writer = b_writer;
		this.kind_id = kind_id;
	}


	
	public String getB_publisher() {
		return b_publisher;
	}
	public void setB_publisher(String b_publisher) {
		this.b_publisher = b_publisher;
	}
	
	public int getB_id() {
		return b_id;
	}
	
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getB_writer() {
		return b_writer;
	}
	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}

	
	public Integer getKind_id() {
		return kind_id;
	}
	public void setKind_id(Integer kind_id) {
		this.kind_id = kind_id;
	}
	public String getB_description() {
		return b_description;
	}
	public void setB_description(String b_description) {
		this.b_description = b_description;
	}

	public String getB_status_id() {
		return b_status_id;
	}

	public void setB_status_id(String b_status_id) {
		this.b_status_id = b_status_id;
	}
	

}
