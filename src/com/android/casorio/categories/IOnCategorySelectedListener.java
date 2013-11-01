package com.android.casorio.categories;

public interface IOnCategorySelectedListener {
	
	
	public void onCreateCategory();
	
	public void onCategorySelected(long position);
	
	public void onCategoryUpdated(long categoryId);

}
