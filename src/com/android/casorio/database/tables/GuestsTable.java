package com.android.casorio.database.tables;

public class GuestsTable {
	
	
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
	public static final String CREATE_GUESTS_TABLE = "create table "
			+ TABLE_GUESTS + "(" 
			+ COLUMN_ID	+ " integer primary key autoincrement, " 
			+ COLUMN_NAME + " text not null,"
			+ COLUMN_EMAIL + " text,"
			+ COLUMN_ADDITIONAL_GUESTS + " integer,"
			+ COLUMN_TYPE + " integer,"
			+ COLUMN_STATUS + " text not null);";


}
