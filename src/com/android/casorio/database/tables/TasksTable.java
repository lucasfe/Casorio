package com.android.casorio.database.tables;

import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TasksTable {
	
	
	/**
	 * loaded categories name prefix
	 */
	public static final String TASKS_NAME_PREFIX = "PREDEFINED_TASK";
	
	/**
	 * Section for table tasks
	 */
	public static final String TABLE_NAME = "tasks";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_CATEGORY_ID = "id_category";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DUE_DATE = "due_date";
	public static final String COLUMN_COAST = "coast";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_NOTE = "notes";
	public static final String COLUMN_PREDEFINED_TASK_NAME_PREFIX = "predefined_task";

	
	/**
	 * Database creation statement 
	 */
	public static final String CREATE_TASKS_TABLE = "create table "
			+ TABLE_NAME + "(" 
			+ COLUMN_ID	+ " integer primary key autoincrement, " 
			+ COLUMN_CATEGORY_ID + " integer default 0,"
			+ COLUMN_NAME + " text not null,"
			+ COLUMN_DUE_DATE + " integer default 0,"
			+ COLUMN_NOTE + " text,"
			+ COLUMN_STATUS + " integer default 0,"
			+ COLUMN_COAST + " integer,"
			+ COLUMN_PREDEFINED_TASK_NAME_PREFIX + "  integer default 0);";
	
	
	/**
	 * This method will insert some quotes on database that will be used before
	 * receiving new values from network
	 * 
	 * @param database
	 *            the database object to be insert
	 */
	public static void loadPredefinedTasks(SQLiteDatabase database, Context context) {
		ContentValues initialValues = new ContentValues();

		long date = Calendar.getInstance().getTimeInMillis();
		
		for (int i = 0; i <= 3; i++) {
			initialValues.put(COLUMN_NAME, TASKS_NAME_PREFIX);
			initialValues.put(COLUMN_PREDEFINED_TASK_NAME_PREFIX, i);
			initialValues.put(COLUMN_CATEGORY_ID, 1);
			initialValues.put(COLUMN_COAST, 0);
			initialValues.put(COLUMN_NOTE, "");
			initialValues.put(COLUMN_DUE_DATE, date);

			
			database.insert(TABLE_NAME, null, initialValues);
		}
		
		initialValues = new ContentValues();
		for (int i = 4; i <= 7; i++) {
			initialValues.put(COLUMN_NAME, TASKS_NAME_PREFIX);
			initialValues.put(COLUMN_PREDEFINED_TASK_NAME_PREFIX, i);
			initialValues.put(COLUMN_CATEGORY_ID, 2);
			initialValues.put(COLUMN_COAST, 0);
			initialValues.put(COLUMN_NOTE, "");
			initialValues.put(COLUMN_DUE_DATE, date);
			database.insert(TABLE_NAME, null, initialValues);
		}
		
		initialValues = new ContentValues();
		for (int i = 8; i <= 10; i++) {
			initialValues.put(COLUMN_NAME, TASKS_NAME_PREFIX);
			initialValues.put(COLUMN_PREDEFINED_TASK_NAME_PREFIX, i);
			initialValues.put(COLUMN_CATEGORY_ID, 3);
			initialValues.put(COLUMN_COAST, 0);
			initialValues.put(COLUMN_NOTE, "");
			initialValues.put(COLUMN_DUE_DATE, date);
			database.insert(TABLE_NAME, null, initialValues);
		}

		initialValues = new ContentValues();
		for (int i = 11; i <= 14; i++) {
			initialValues.put(COLUMN_NAME, TASKS_NAME_PREFIX);
			initialValues.put(COLUMN_PREDEFINED_TASK_NAME_PREFIX, i);
			initialValues.put(COLUMN_CATEGORY_ID, 4);
			initialValues.put(COLUMN_COAST, 0);
			initialValues.put(COLUMN_NOTE, "");
			initialValues.put(COLUMN_DUE_DATE, date);
			database.insert(TABLE_NAME, null, initialValues);
		}
		
		initialValues = new ContentValues();
		for (int i = 15; i <= 16; i++) {
			initialValues.put(COLUMN_NAME, TASKS_NAME_PREFIX);
			initialValues.put(COLUMN_PREDEFINED_TASK_NAME_PREFIX, i);
			initialValues.put(COLUMN_CATEGORY_ID, 5);
			initialValues.put(COLUMN_COAST, 0);
			initialValues.put(COLUMN_NOTE, "");
			initialValues.put(COLUMN_DUE_DATE, date);
			database.insert(TABLE_NAME, null, initialValues);
		}

		initialValues = new ContentValues();
		for (int i = 17; i <= 19; i++) {
			initialValues.put(COLUMN_NAME, TASKS_NAME_PREFIX);
			initialValues.put(COLUMN_PREDEFINED_TASK_NAME_PREFIX, i);
			initialValues.put(COLUMN_CATEGORY_ID, 6);
			initialValues.put(COLUMN_COAST, 0);
			initialValues.put(COLUMN_NOTE, "");
			initialValues.put(COLUMN_DUE_DATE, date);
			database.insert(TABLE_NAME, null, initialValues);
		}

		initialValues = new ContentValues();
		for (int i = 20; i <= 23; i++) {
			initialValues.put(COLUMN_NAME, TASKS_NAME_PREFIX);
			initialValues.put(COLUMN_PREDEFINED_TASK_NAME_PREFIX, i);
			initialValues.put(COLUMN_CATEGORY_ID, 7);
			initialValues.put(COLUMN_COAST, 0);
			initialValues.put(COLUMN_NOTE, "");
			initialValues.put(COLUMN_DUE_DATE, date);
			database.insert(TABLE_NAME, null, initialValues);
		}
		
		initialValues = new ContentValues();
		for (int i = 24; i <= 24; i++) {
			initialValues.put(COLUMN_NAME, TASKS_NAME_PREFIX);
			initialValues.put(COLUMN_PREDEFINED_TASK_NAME_PREFIX, i);
			initialValues.put(COLUMN_CATEGORY_ID, 8);
			initialValues.put(COLUMN_COAST, 0);
			initialValues.put(COLUMN_NOTE, "");
			initialValues.put(COLUMN_DUE_DATE, date);
			database.insert(TABLE_NAME, null, initialValues);
		}
		
		initialValues = new ContentValues();
		for (int i = 25; i <= 28; i++) {
			initialValues.put(COLUMN_NAME, TASKS_NAME_PREFIX);
			initialValues.put(COLUMN_PREDEFINED_TASK_NAME_PREFIX, i);
			initialValues.put(COLUMN_CATEGORY_ID, 9);
			initialValues.put(COLUMN_COAST, 0);
			initialValues.put(COLUMN_NOTE, "");
			initialValues.put(COLUMN_DUE_DATE, date);
			database.insert(TABLE_NAME, null, initialValues);
		}
		
		initialValues = new ContentValues();
		for (int i = 29; i <= 33; i++) {
			initialValues.put(COLUMN_NAME, TASKS_NAME_PREFIX);
			initialValues.put(COLUMN_PREDEFINED_TASK_NAME_PREFIX, i);
			initialValues.put(COLUMN_CATEGORY_ID, 10);
			initialValues.put(COLUMN_COAST, 0);
			initialValues.put(COLUMN_NOTE, "");
			initialValues.put(COLUMN_DUE_DATE, date);
			database.insert(TABLE_NAME, null, initialValues);
		}




	}
	

}
