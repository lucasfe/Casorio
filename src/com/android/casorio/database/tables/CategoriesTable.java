package com.android.casorio.database.tables;

public class CategoriesTable {
	
	/**
	 * Section for table tasks
	 */
	public static final String TABLE_NAME = "categories";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_COAST = "coast";
	
	
	/**
	 * Database creation statement 
	 */
	public static final String CREATE_CATEGORIES_TABLE = "create table "
			+ TABLE_NAME + "(" 
			+ COLUMN_ID	+ " integer primary key autoincrement, " 
			+ COLUMN_NAME + " text not null,"
			+ COLUMN_COAST + " integer);";



}
