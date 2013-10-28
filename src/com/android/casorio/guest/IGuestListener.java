package com.android.casorio.guest;

public interface IGuestListener {
	
	public void onGuestSelected(int position);
	
	public void onInsertGuest();
	
	public void onGuestDeleted(long id);
	
	public void onGuestEdit(long id);

}
