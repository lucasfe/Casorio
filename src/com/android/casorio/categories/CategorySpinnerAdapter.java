package com.android.casorio.categories;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lucasfe.casorio.R;
import com.android.casorio.database.datasources.TaskDataSource;

public class CategorySpinnerAdapter extends BaseAdapter {

	LayoutInflater mInflater;

	TextView nameTextView;
	TextView balanceTextView;
	TextView budgetTextView;
	TextView statusTextView;
	
	Context mContext;
	
	TaskDataSource mTaskDataSource;

	private List<Category> categoriesArray;

	public CategorySpinnerAdapter(List<Category> categoryArray, Context context) {
		mInflater = LayoutInflater.from(context);
		categoriesArray = categoryArray;
		mTaskDataSource = new TaskDataSource(context);
		mContext = context;

	}

	@Override
	public int getCount() {
		return categoriesArray.size();
	}

	@Override
	public Object getItem(int position) {
		return categoriesArray.get(position);
	}

	@Override
	public long getItemId(int position) {
				
		return categoriesArray.get(position).getId();
	}
	
	public int getPositionByCategory(Category category) {
		
		return categoriesArray.indexOf(category);
	}
	
	public int getPositionByCategoryId(long categoryId) {
		
		Category cat = new Category(mContext);
		cat.setId(categoryId);
		
		return categoriesArray.indexOf(cat);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater
					.inflate(R.layout.category_spinner_item_layout, null);
		}

		Category category = categoriesArray.get(position);
		
		nameTextView = (TextView) convertView
				.findViewById(R.id.category_spinner_item);

		nameTextView.setText(category.getName());
		
		return convertView;
	}
}
