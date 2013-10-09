package com.android.casorio.categories;

public class Category {
	
	
	private long _id;
	private long coast;
	private String name;

	public long getId() {
		return _id;
	}
	public void setId(long id) {
		this._id = id;
	}	
	public long getBudget() {
		return coast;
	}
	public void setBudget(long coast) {
		this.coast = coast;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getBalance() {
		return "";
	}
	
	public String getStatus() {
		return "Positivo";
	}

}
