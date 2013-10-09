package com.android.casorio.categories;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.casorio.R;

public class CategoryListAdapter extends BaseAdapter {

	LayoutInflater mInflater;

	TextView nameTextView;
	TextView balanceTextView;
	TextView budgetTextView;
	TextView statusTextView;

	private List<Category> mergedArray;

	public CategoryListAdapter(List<Category> categoryArray, Context context) {
		mInflater = LayoutInflater.from(context);
		mergedArray = categoryArray;

	}

	public CategoryListAdapter(List<Category> collection1,
			List<Category> collection2, Context context) {
		mInflater = LayoutInflater.from(context);
		mergedArray = collection2;
		mergedArray.addAll(collection1);
	}

	@Override
	public int getCount() {
		return mergedArray.size();
	}

	@Override
	public Object getItem(int position) {
		return mergedArray.get(position);
	}

	@Override
	public long getItemId(int position) {
				
		return mergedArray.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater
					.inflate(R.layout.category_item_layout, null);
		}

		Category category = mergedArray.get(position);

		nameTextView = (TextView) convertView
				.findViewById(R.id.category_NameTxtView);
		budgetTextView = (TextView) convertView
				.findViewById(R.id.category_BudgetTxtView);
		balanceTextView = (TextView) convertView
				.findViewById(R.id.category_BalanceTxtView);
		statusTextView = (TextView) convertView
				.findViewById(R.id.category_StatusTxtView);

		nameTextView.setText(category.getName());
		balanceTextView.setText(category.getBalance());
		budgetTextView.setText(String.valueOf(category.getBudget()));
		statusTextView.setText(category.getStatus());
		return convertView;
	}

}
