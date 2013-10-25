package com.android.casorio.guest;

import java.io.Serializable;

import com.android.casorio.R;

import android.content.Context;


public class Guest implements Serializable{
	
	
	
	/**
	 * Serial UID for Serializable interface 
	 */
	private static final long serialVersionUID = 1L;

	
	private long id;
	private String name;
	private String email;
	private int additinal_guests;
	private int type;

	private int status;
	

	public enum GuestStatus {
		
		NOT_GOING(2),
		
		CONFIRMED(1),
		
		PENDING(0); 
				
		private int statValue;
		
		private GuestStatus(int value) {
			this.statValue = value;
		}
		
		public int getValue() {
			return statValue;
		}
		
		public String getStatusText(Context context) {
			String result = "";
			
			switch (this) {
			case NOT_GOING:
				result = context.getResources().getString(
						R.string.guest_not_going);
				break;
			case PENDING:
				result = context.getResources().getString(
						R.string.guest_pending_status);
				break;
			case CONFIRMED:
				result = context.getResources().getString(
						R.string.guest_confirmed_status);
				break;
			default:

			}
			return result;

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

	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
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
		return GuestStatus.values()[status];
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
