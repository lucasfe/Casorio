package com.android.casorio.database;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public abstract class GenericDataSource {
	
	// Database fields
	protected SQLiteDatabase database;
	protected CasorioDatabase dbHelper;

	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}


}
