package com.android.casorio.categories;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.casorio.R;
import com.android.casorio.database.datasources.CategoriesDataSource;

public class CategoryListFragment extends Fragment {

	ListView categoriesList;
	
	CategoriesDataSource mDataSource;

	private IOnCategorySelectedListener mCategorySelected;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.category_list_layout,
				container, false);

		setHasOptionsMenu(true);
		mDataSource = new CategoriesDataSource(getActivity());
		mDataSource.open();

		final CategoryListAdapter categoryAdapter = new CategoryListAdapter(
				mDataSource.getAllCategories(), getActivity());

		categoriesList = (ListView) rootView
				.findViewById(R.id.category_listView);
		categoriesList.setAdapter(categoryAdapter);

		categoriesList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mCategorySelected.onCategorySelected(categoryAdapter.getItemId(position));
			}
		});

		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		inflater.inflate(R.menu.generic_list_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_call_insert:
			mCategorySelected.onCreateCategory();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		if ((activity instanceof IOnCategorySelectedListener)) {
			mCategorySelected = (IOnCategorySelectedListener) activity;

		} else {
			throw new ClassCastException(activity.toString()
					+ "must implement IOnCategorySelectedListener");
		}
	}

}
