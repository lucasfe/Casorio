package com.android.casorio.categories;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.lucasfe.casorio.R;
import com.android.casorio.database.datasources.CategoriesDataSource;
import com.android.casorio.util.Validator;

public class CreateCategoriesFragment extends Fragment {

	EditText nameTxt;
	EditText coastTxt;

	CategoriesDataSource mCategoriesDataSource;

	Category category;

	public static final String CATEGORY_ID = "categoryId";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mCategoriesDataSource = new CategoriesDataSource(getActivity());

		setHasOptionsMenu(true);

		View rootView = inflater.inflate(R.layout.category_create_layout,
				container, false);

		nameTxt = (EditText) rootView.findViewById(R.id.categories_create_name);
		coastTxt = (EditText) rootView
				.findViewById(R.id.categories_create_coast);

		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		inflater.inflate(R.menu.generic_insert_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_save:
			insertOrUpdateCategory();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onStart() {
		super.onStart();
		category = null;
		Bundle args = getArguments();
		if (args != null) {
			long categoryId = args.getLong(CATEGORY_ID);
			mCategoriesDataSource.open();
			category = mCategoriesDataSource.getCategoryById(categoryId);
			fillUI(category);
			mCategoriesDataSource.close();
		}
	}

	private void fillUI(Category category) {
		nameTxt.setText(category.getName());
		coastTxt.setText(String.valueOf(category.getBudget()));
	}
	
	private void insertOrUpdateCategory() {
		if (category == null) { 
			insertCategory();	
		}
		else {
			updateCategory();
		}
	}


	private boolean insertCategory() {

		if (!Validator.hasText(getActivity(), nameTxt)
				|| !Validator.isNumber(getActivity(), coastTxt, true)) {
			return false;
		}

		mCategoriesDataSource.open();
		mCategoriesDataSource.createCategory(nameTxt.getText().toString(),
				Long.parseLong(coastTxt.getText().toString()));
		mCategoriesDataSource.close();

		Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.category_insert_created), Toast.LENGTH_SHORT).show();
		return true;

	}
	
	private boolean updateCategory() {
		
		if (!Validator.hasText(getActivity(), nameTxt)
				|| !Validator.isNumber(getActivity(), coastTxt, true)) {
			return false;
		}

		category.setName(nameTxt.getText().toString());
		category.setBudget(Long.valueOf(coastTxt.getText().toString()));
		
		mCategoriesDataSource.open();
		mCategoriesDataSource.updateCategory(category);
		mCategoriesDataSource.close();

		Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.category_insert_updated), Toast.LENGTH_SHORT).show();
		return true;

		
	}

}
