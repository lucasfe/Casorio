package com.android.casorio.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.casorio.guest.Guest;
import com.android.casorio.guest.Guest.GuestStatus;
import com.android.casorio.guest.Guest.GuestType;

public class GuestDataSource {
	
	private String[] allColumns = { CasorioDatabase.COLUMN_ID, CasorioDatabase.COLUMN_NAME, 
			CasorioDatabase.COLUMN_EMAIL,  CasorioDatabase.COLUMN_ADDITIONAL_GUESTS, CasorioDatabase.COLUMN_TYPE, CasorioDatabase.COLUMN_STATUS };
	
	// Database fields
	private SQLiteDatabase database;
	private CasorioDatabase dbHelper;
	
	public GuestDataSource(Context context) {
		dbHelper = new CasorioDatabase(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Guest createGuest(String name, String email, int additionalGuest, GuestType type, GuestStatus status) {
		ContentValues values = new ContentValues();
		
		values.put(CasorioDatabase.COLUMN_NAME, name);
		values.put(CasorioDatabase.COLUMN_EMAIL, email);
		values.put(CasorioDatabase.COLUMN_ADDITIONAL_GUESTS, additionalGuest);
		values.put(CasorioDatabase.COLUMN_TYPE, type.getValue());
		values.put(CasorioDatabase.COLUMN_STATUS, status.getValue());
		
		
		long insertId = database.insert(CasorioDatabase.TABLE_GUESTS, null,
				values);
		Cursor cursor = database.query(CasorioDatabase.TABLE_GUESTS,
				allColumns, CasorioDatabase.COLUMN_ID + " = " + insertId, null,
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

		Cursor cursor = database.query(CasorioDatabase.TABLE_GUESTS,
				allColumns, null, null, null, null, null);

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
		Cursor cursor = database.query(CasorioDatabase.TABLE_GUESTS,
				allColumns, null, null, null, null, null);
		return cursor;
	}




}
