package com.android.casorio.categories;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.casorio.R;
import com.android.casorio.database.datasources.CategoriesDataSource;

public class CategoryListFragment extends Fragment {

	ListView categoriesList;

	CategoriesDataSource mDataSource;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.category_list_layout,
				container, false);

		setHasOptionsMenu(true);
		mDataSource = new CategoriesDataSource(getActivity());
		mDataSource.open();

		CategoryListAdapter categoryAdapter = new CategoryListAdapter(mDataSource.getAllCategories(), getActivity());

		categoriesList = (ListView) rootView
				.findViewById(R.id.category_listView);
		categoriesList.setAdapter(categoryAdapter);

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
			FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			Fragment destiny = new CreateCategoriesFragment();
			transaction.replace(R.id.content_frame, destiny).commit();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
