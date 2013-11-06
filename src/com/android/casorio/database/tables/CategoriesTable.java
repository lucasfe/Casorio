package com.android.casorio.database.tables;

import com.android.casorio.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CategoriesTable {

	/**
	 * Section for table tasks
	 */
	public static final String TABLE_NAME = "categories";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_PREDEFINED_CATEGORY = "predefined_category";
	public static final String COLUMN_COAST = "coast";

	/**
	 * Database creation statement
	 */
	public static final String CREATE_CATEGORIES_TABLE = "create table "
			+ TABLE_NAME + "(" + 
			COLUMN_ID + " integer primary key autoincrement, " + 
			COLUMN_NAME + " text not null," + 
			COLUMN_PREDEFINED_CATEGORY + " integer default 0," + 
			COLUMN_COAST + " integer);";

	/**
	 * loaded categories name prefix
	 */
	public static final String CATEGORIES_NAME_PREFIX = "PREDEFINED_CATEGORY";

	/**
	 * This method will insert some quotes on database that will be used before
	 * receiving new values from network
	 * 
	 * @param database
	 *            the database object to be insert
	 */
	public static void loadPredefinedCategories(SQLiteDatabase database, Context context) {
		ContentValues initialValues = new ContentValues();

		String[] categoriesArray = context.getResources().getStringArray(R.array.task_category_options);	
		for (int i = 0; i < categoriesArray.length; i++) {
			initialValues.put(COLUMN_NAME, CATEGORIES_NAME_PREFIX);
			initialValues.put(COLUMN_PREDEFINED_CATEGORY, i);
			database.insert(TABLE_NAME, null, initialValues);
		}

	}

}
