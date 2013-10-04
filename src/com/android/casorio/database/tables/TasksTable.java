package com.android.casorio.database.tables;

public class TasksTable {
	
	/**
	 * Section for table tasks
	 */
	public static final String TABLE_TASKS = "guests";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_CATEGORY_ID = "id_category";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DUE_DATE = "due_date";
	public static final String COLUMN_REMINDER = "reminder";
	public static final String COLUMN_COAST = "coast";
	public static final String COLUMN_NOTE = "notes";
	
	
	/**
	 * Database creation statement 
	 */
	public static final String CREATE_TASKS_TABLE = "create table "
			+ TABLE_TASKS + "(" 
			+ COLUMN_ID	+ " integer primary key autoincrement, " 
			+ COLUMN_CATEGORY_ID + " integer,"
			+ COLUMN_NAME + " text not null,"
			+ COLUMN_DUE_DATE + " datetime,"
			+ COLUMN_REMINDER + " datetime,"
			+ COLUMN_NOTE + " text,"
			+ COLUMN_COAST + " integer);";



}
