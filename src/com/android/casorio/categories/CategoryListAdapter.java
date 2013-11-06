package com.android.casorio.categories;

import java.text.NumberFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lucasfe.casorio.R;
import com.android.casorio.database.datasources.TaskDataSource;

public class CategoryListAdapter extends BaseAdapter {

	LayoutInflater mInflater;

	TextView nameTextView;
	TextView balanceTextView;
	TextView budgetTextView;
	TextView statusTextView;
	
	Context mContext;
	
	TaskDataSource mTaskDataSource;

	private List<Category> categoriesArray;

	public CategoryListAdapter(List<Category> categoryArray, Context context) {
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater
					.inflate(R.layout.category_item_layout, null);
		}

		Category category = categoriesArray.get(position);
		
		//format currency string according to its location
		long balance = category.getBalance();
		String balanceFormated = NumberFormat.getCurrencyInstance().format(balance);
		String budgetFormated = NumberFormat.getCurrencyInstance().format(category.getBudget());
		
		nameTextView = (TextView) convertView
				.findViewById(R.id.category_NameTxtView);
		budgetTextView = (TextView) convertView
				.findViewById(R.id.category_BudgetTxtView);
		balanceTextView = (TextView) convertView
				.findViewById(R.id.category_BalanceTxtView);
		statusTextView = (TextView) convertView
				.findViewById(R.id.category_StatusTxtView);

		nameTextView.setText(category.getName());
		balanceTextView.setText(balanceFormated);
		budgetTextView.setText(budgetFormated);
		
		
		if (balance >= 0) {
			statusTextView.setText(mContext.getString(R.string.category_list_status_remaining));	
			statusTextView.setTextColor(mContext.getResources().getColor(android.R.color.holo_green_light));
		} 
		else {
			statusTextView.setText(mContext.getString(R.string.category_list_status_over));			
			statusTextView.setTextColor(mContext.getResources().getColor(android.R.color.holo_red_light));
		}
		
		return convertView;
	}
	
}
