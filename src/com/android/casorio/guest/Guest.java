package com.android.casorio.guest;

import java.io.Serializable;


public class Guest implements Serializable{
	
	
	/**
	 * Serial UID for Serializable interface 
	 */
	private static final long serialVersionUID = 1L;

	
	private long id;
	private String name;
	private String email;
	private int additinal_guests;
	private int status;
	

	public enum GuestStatus {
		
		NOT_GOING(3),
		
		CONFIRMED(2),
		
		PENDING(1), 
		
		INVITE_SENT(0);
		
		private int statValue;
		
		private GuestStatus(int value) {
			this.statValue = value;
		}
		
		public int getValue() {
			return statValue;
		}
				
	};
	

	public enum GuestType {
		
		FRIEND(2),
		
		RElATIVE(1),

		GODFATHER(0);
		
		
		private int statValue;
		
		private GuestType(int value) {
			this.statValue = value;
		}
		
		public int getValue() {
			return statValue;
		}
				
	};

	
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
	
	public GuestStatus getGuestStatus() {
		
		GuestStatus result = GuestStatus.INVITE_SENT;
		
		
		int value = this.status;
		
		switch(value) 
		{
		case 3:
			result = GuestStatus.NOT_GOING;
			break;	
		case 2:
			result = GuestStatus.CONFIRMED;
			break;
		case 1:
			result = GuestStatus.PENDING;
			break;
		case 0:
			result = GuestStatus.INVITE_SENT;
			break;
		default:
		
		}
		
		return result;
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
