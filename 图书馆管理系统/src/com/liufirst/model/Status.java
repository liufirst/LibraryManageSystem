package com.liufirst.model;
/**
 * ͼ��״̬ʵ��
 * 0-�ڿ�
 * 1-����
 * @author 25833
 *
 */
public class Status {
	private int status_id;
	private String status_name;
	
	public Status() {
		super();
	}

	public Status(int status_id, String status_name) {
		super();
		this.status_id = status_id;
		this.status_name = status_name;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	@Override
	public String toString() {
		return status_name;
	}

}
