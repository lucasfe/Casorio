package com.android.casorio.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CasorioDatabase extends SQLiteOpenHelper {
	
	
	public CasorioDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Database fields for name and version
	 */
	private static final String DATABASE_NAME = "quotes.db";
	private static final int DATABASE_VERSION = 1;

	/**
	 * Section for table guests
	 */
	public static final String TABLE_GUESTS = "guests";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_ADDITIONAL_GUESTS = "additional_guests";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_STATUS = "status";
	
	
	/**
	 * Database creation statement 
	 */
	private static final String CREATE_GUESTS_TABLE = "create table "
			+ TABLE_GUESTS + "(" 
			+ COLUMN_ID	+ " integer primary key autoincrement, " 
			+ COLUMN_NAME + " text not null,"
			+ COLUMN_EMAIL + " text,"
			+ COLUMN_ADDITIONAL_GUESTS + " integer,"
			+ COLUMN_TYPE + " integer,"
			+ COLUMN_STATUS + " text not null);";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_GUESTS_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(CasorioDatabase.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUESTS);
		onCreate(db);
		
	}


}
