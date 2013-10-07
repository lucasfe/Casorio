package com.android.casorio.database;

import java.io.Serializable;

public class Task implements Serializable {

	/**
	 * Serial UID for Serializable interface
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String name;
	private String category_id;
	private int coast;
	private String dueDate;
	private String note;
	private String remimder;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public int getCoast() {
		return coast;
	}

	public void setCoast(int coast) {
		this.coast = coast;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRemimder() {
		return remimder;
	}

	public void setRemimder(String remimder) {
		this.remimder = remimder;
	}

}
