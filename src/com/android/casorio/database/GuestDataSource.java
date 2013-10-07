package com.android.casorio.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.android.casorio.database.tables.GuestsTable;
import com.android.casorio.guest.Guest;
import com.android.casorio.guest.Guest.GuestStatus;
import com.android.casorio.guest.Guest.GuestType;

public class GuestDataSource extends GenericDataSource {
	
	private String[] allColumns = { GuestsTable.COLUMN_ID, GuestsTable.COLUMN_NAME, 
			GuestsTable.COLUMN_EMAIL,  GuestsTable.COLUMN_ADDITIONAL_GUESTS, GuestsTable.COLUMN_TYPE, GuestsTable.COLUMN_STATUS };
	
	
	public GuestDataSource(Context context) {
		dbHelper = new CasorioDatabase(context);
	}


	public Guest createGuest(String name, String email, int additionalGuest, GuestType type, GuestStatus status) {
		ContentValues values = new ContentValues();
		
		values.put(GuestsTable.COLUMN_NAME, name);
		values.put(GuestsTable.COLUMN_EMAIL, email);
		values.put(GuestsTable.COLUMN_ADDITIONAL_GUESTS, additionalGuest);
		values.put(GuestsTable.COLUMN_TYPE, type.getValue());
		values.put(GuestsTable.COLUMN_STATUS, status.getValue());
		
		
		long insertId = database.insert(GuestsTable.TABLE_GUESTS, null,
				values);
		Cursor cursor = database.query(GuestsTable.TABLE_GUESTS,
				allColumns, GuestsTable.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Guest newGuest = cursorToGuest(cursor);
		cursor.close();
		return newGuest;
	}
	
	public static Guest cursorToGuest(Cursor cursor) {
		
		Guest newGuest = new Guest();
		newGuest.setId(cursor.getLong(0));
		newGuest.setName(cursor.getString(1));
		newGuest.setEmail(cursor.getString(2));
		newGuest.setAdditinal_guests(cursor.getInt(3));
		newGuest.setAdditinal_guests(cursor.getInt(4));
		newGuest.setStatus(cursor.getInt(5));
		return newGuest;
	}
	
	
	public List<Guest> getAllGuests() {
		List<Guest> guests = new ArrayList<Guest>();

		Cursor cursor = getAllGuestsCursor();

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Guest guest = cursorToGuest(cursor);
			guests.add(guest);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return guests;
	}
	
	public Cursor getAllGuestsCursor() {
		Cursor cursor = database.query(GuestsTable.TABLE_GUESTS,
				allColumns, null, null, null, null, null);
		return cursor;
	}




}
