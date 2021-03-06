package com.android.casorio.database.datasources;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.android.casorio.database.CasorioDatabase;
import com.android.casorio.database.GenericDataSource;
import com.android.casorio.database.tables.GuestsTable;
import com.android.casorio.guest.Guest;
import com.android.casorio.guest.Guest.GuestStatus;
import com.android.casorio.guest.Guest.GuestType;

public class GuestDataSource extends GenericDataSource {

	private String[] allColumns = { GuestsTable.COLUMN_ID,
			GuestsTable.COLUMN_NAME, GuestsTable.COLUMN_EMAIL,
			GuestsTable.COLUMN_ADDITIONAL_GUESTS, GuestsTable.COLUMN_TYPE,
			GuestsTable.COLUMN_STATUS };

	public GuestDataSource(Context context) {
		dbHelper = new CasorioDatabase(context);
	}

	public Guest createGuest(String name, String email, int additionalGuest,
			GuestType type, GuestStatus status) {
		ContentValues values = new ContentValues();

		values.put(GuestsTable.COLUMN_NAME, name);
		values.put(GuestsTable.COLUMN_EMAIL, email);
		values.put(GuestsTable.COLUMN_ADDITIONAL_GUESTS, additionalGuest);
		values.put(GuestsTable.COLUMN_TYPE, type.getValue());
		values.put(GuestsTable.COLUMN_STATUS, status.getValue());

		long insertId = database.insert(GuestsTable.TABLE_GUESTS, null, values);

		return getGuestById(insertId);
	}

	public void updateGuest(Guest guest) {

		ContentValues values = new ContentValues();

		values.put(GuestsTable.COLUMN_NAME, guest.getName());
		values.put(GuestsTable.COLUMN_EMAIL, guest.getEmail());
		values.put(GuestsTable.COLUMN_ADDITIONAL_GUESTS,
				guest.getAdditinal_guests());
		values.put(GuestsTable.COLUMN_TYPE, guest.getType());
		values.put(GuestsTable.COLUMN_STATUS, guest.getGuestStatus().getValue());

		database.update(GuestsTable.TABLE_GUESTS, values, GuestsTable.COLUMN_ID
				+ "=?", new String[] { String.valueOf(guest.getId()) });

	}

	public void deleteGuest(long id) {
		database.delete(GuestsTable.TABLE_GUESTS, GuestsTable.COLUMN_ID + "=?",
				new String[] { String.valueOf(id) });
	}

	public static Guest cursorToGuest(Cursor cursor) {

		Guest newGuest = null;
		if (cursor.getCount() >= 1) {
			newGuest = new Guest();
			newGuest.setId(cursor.getLong(0));
			newGuest.setName(cursor.getString(1));
			newGuest.setEmail(cursor.getString(2));
			newGuest.setAdditinal_guests(cursor.getInt(3));
			newGuest.setType(cursor.getInt(4));
			newGuest.setStatus(cursor.getInt(5));
		}

		return newGuest;
	}

	public List<Guest> getAllGuests() {
		Cursor cursor = getAllGuestsCursor();
		List<Guest> result = cursorToGuestList(cursor);
		cursor.close();
		return result;
	}

	public Cursor getAllGuestsCursor() {
		Cursor cursor = database.query(GuestsTable.TABLE_GUESTS, allColumns,
				null, null, null, null, null);
		return cursor;
	}

	public Guest getGuestById(long id) {
		Cursor cursor = database.query(GuestsTable.TABLE_GUESTS, allColumns,
				GuestsTable.COLUMN_ID + " = " + id, null, null, null, null);
		cursor.moveToFirst();
		Guest newGuest = cursorToGuest(cursor);
		cursor.close();
		return newGuest;

	}

	public int getNumberOfGuests() {

		int totalGuests = 0;
		for (Guest currGuest : getAllGuests()) {
			if (currGuest.getGuestStatus() != GuestStatus.NOT_GOING) {
				// sums current guest and its additionals
				totalGuests += currGuest.getAdditinal_guests() + 1;
			}
		}
		return totalGuests;
	}

	public int getNumberOfGuestsFromCursor(Cursor cursor) {

		int totalGuests = 0;
		for (Guest currGuest : cursorToGuestList(cursor)) {
			if (currGuest.getGuestStatus() != GuestStatus.NOT_GOING) {
				// sums current guest and its additionals
				totalGuests += currGuest.getAdditinal_guests() + 1;
			}

		}
		return totalGuests;
	}

	public List<Guest> cursorToGuestList(Cursor cursor) {

		List<Guest> guests = new ArrayList<Guest>();

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Guest guest = cursorToGuest(cursor);
			guests.add(guest);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		return guests;

	}

}
