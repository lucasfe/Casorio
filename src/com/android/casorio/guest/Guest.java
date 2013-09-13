package com.android.casorio.guest;

public class Guest {
	
	private long id;
	private String name;
	private String lastName;
	private String email;
	private String telephony;
	private int additinal_guests;
	private int status;
	
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelephony() {
		return telephony;
	}
	
	public void setTelephony(String telephony) {
		this.telephony = telephony;
	}
	
	public int getAdditinal_guests() {
		return additinal_guests;
	}
	
	public void setAdditinal_guests(int additinal_guests) {
		this.additinal_guests = additinal_guests;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
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
	

}
