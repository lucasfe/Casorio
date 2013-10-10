package com.android.casorio.categories;

import java.util.Arrays;
import java.util.List;

import com.android.casorio.R;
import com.android.casorio.database.tables.CategoriesTable;

import android.content.Context;
import android.text.TextUtils;

public class Category {
	
	
	private Context mContext;
	
	private long _id;
	private long coast;
	private String name;
	private int category_prefix;

	public Category(Context context) {
		mContext = context;
	}
	
	public int getCategory_prefix() {
		return category_prefix;
	}
	public void setCategory_prefix(int category_prefix) {
		this.category_prefix = category_prefix;
	}
	public long getId() {
		return _id;
	}
	public void setId(long id) {
		this._id = id;
	}	
	public long getBudget() {
		return coast;
	}
	public void setBudget(long coast) {
		this.coast = coast;
	}
	public String getName() {
		
		if(!TextUtils.isEmpty(name) && name.contains(CategoriesTable.CATEGORIES_NAME_PREFIX)) {
			return  getPredefinedCategoryString();
		}
		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getBalance() {
		return "";
	}
	
	public String getStatus() {
		return "Positivo";
	}
	
	private String getPredefinedCategoryString() {
		
		List<String> categories = Arrays.asList( mContext.getResources().getStringArray(R.array.task_category_options));
		
		return categories.get(getCategory_prefix());
		
	}

}
