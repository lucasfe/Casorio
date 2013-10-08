package com.android.casorio.categories;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CategoryListAdapter extends CursorAdapter {

	LayoutInflater mInflater;
	
	
	@SuppressWarnings("deprecation")
	public CategoryListAdapter(Context context, Cursor c) {
		super(context, c);
		mInflater = LayoutInflater.from(context);

	
	}

	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup rootView) {
		// TODO Auto-generated method stub
		return null;
	}

}
