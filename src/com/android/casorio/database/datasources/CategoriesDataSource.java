package com.android.casorio.database.datasources;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.android.casorio.R;
import com.android.casorio.categories.Category;
import com.android.casorio.categories.PredefinedCategory;
import com.android.casorio.database.CasorioDatabase;
import com.android.casorio.database.GenericDataSource;
import com.android.casorio.database.tables.CategoriesTable;

public class CategoriesDataSource extends GenericDataSource {

	private String[] allColumns = { CategoriesTable.COLUMN_ID, CategoriesTable.COLUMN_NAME, 
			CategoriesTable.COLUMN_COAST};
	
	private Context mContext;
	
	
	public CategoriesDataSource(Context context) {
		mContext = context;
		dbHelper = new CasorioDatabase(context);
	}


	
	public Category createCategory(String name, long value) {
		ContentValues values = new ContentValues();
		
		values.put(CategoriesTable.COLUMN_NAME, name);
		values.put(CategoriesTable.COLUMN_COAST, value);
		
		
		long insertId = database.insert(CategoriesTable.TABLE_NAME, null,
				values);
		Cursor cursor = database.query(CategoriesTable.TABLE_NAME,
				allColumns, CategoriesTable.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Category newCategory = cursorToCategory(cursor);
		cursor.close();
		return newCategory;
	}
	
	public static Category cursorToCategory(Cursor cursor) {
		
		Category category = new Category();
		category.setId(cursor.getLong(0));
		category.setName(cursor.getString(1));
		category.setBudget(cursor.getLong(2));
		return category;
	}
	
	public Cursor getAllCategoriesCursor() {
		
		return database.query(CategoriesTable.TABLE_NAME, allColumns, null, null, null, null, null);
		
	}
	
	public List<Category> getAllCategories() {
		List<Category> resultList = new ArrayList<Category>();
		
		Cursor categoriesRows = getAllCategoriesCursor();
		
		categoriesRows.moveToFirst();
		while(!categoriesRows.isAfterLast()) {
			resultList.add(cursorToCategory(categoriesRows));
			categoriesRows.moveToNext();
		}
		
		return resultList;
	}
	
	public List<Category> getPredefinedsCategories() {
		  String[] categoriesPred = mContext.getResources().getStringArray(R.array.task_category_options);
		  List<Category> resultItems = new ArrayList<Category>();
		  int i = 0;
		  for(String theItem: categoriesPred) {
			  resultItems.add(new PredefinedCategory(theItem, i));
			  i++;
		  }
		return resultItems;
	}

}
