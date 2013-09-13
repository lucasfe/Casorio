package com.android.casorio.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.casorio.guest.Guest;

public class GuestDataSource {
	
	private String[] allColumns = { CasorioDatabase.COLUMN_ID, CasorioDatabase.COLUMN_NAME, CasorioDatabase.COLUMN_LAST_NAME,
			CasorioDatabase.COLUMN_EMAIL, CasorioDatabase.COLUMN_TELEPHONY, CasorioDatabase.COLUMN_ADDITIONAL_GUESTS, CasorioDatabase.COLUMN_STATUS };
	
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

	public Guest createGuest(String name, String lastName, String email, String telephony, int additionalGuest, int status) {
		ContentValues values = new ContentValues();
		
		values.put(CasorioDatabase.COLUMN_NAME, name);
		values.put(CasorioDatabase.COLUMN_LAST_NAME, lastName);
		values.put(CasorioDatabase.COLUMN_EMAIL, email);
		values.put(CasorioDatabase.COLUMN_TELEPHONY, telephony);
		values.put(CasorioDatabase.COLUMN_ADDITIONAL_GUESTS, additionalGuest);
		values.put(CasorioDatabase.COLUMN_STATUS, status);
		
		
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
	
	private Guest cursorToGuest(Cursor cursor) {
		
		Guest newGuest = new Guest();
		newGuest.setId(cursor.getLong(0));
		newGuest.setName(cursor.getString(1));
		newGuest.setLastName(cursor.getString(2));
		newGuest.setEmail(cursor.getString(3));
		newGuest.setTelephony(cursor.getString(4));
		newGuest.setAdditinal_guests(cursor.getInt(5));
		newGuest.setStatus(cursor.getInt(6));
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



}
