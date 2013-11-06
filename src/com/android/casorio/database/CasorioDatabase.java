package com.android.casorio.database;

import com.android.casorio.database.tables.CategoriesTable;
import com.android.casorio.database.tables.GuestsTable;
import com.android.casorio.database.tables.TasksTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CasorioDatabase extends SQLiteOpenHelper {
	
	private Context mContext;
	
	
	public CasorioDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = context;
	}

	/**
	 * Database fields for name and version
	 */
	private static final String DATABASE_NAME = "quotes.db";
	private static final int DATABASE_VERSION = 1;


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(GuestsTable.CREATE_GUESTS_TABLE);
		db.execSQL(TasksTable.CREATE_TASKS_TABLE);
		db.execSQL(CategoriesTable.CREATE_CATEGORIES_TABLE);
		
		CategoriesTable.loadPredefinedCategories(db, mContext);
		TasksTable.loadPredefinedTasks(db, mContext);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(CasorioDatabase.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + GuestsTable.TABLE_GUESTS);
		db.execSQL("DROP TABLE IF EXISTS " + TasksTable.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
		onCreate(db);
		
	}


}
