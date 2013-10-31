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

import com.android.casorio.R;
import com.android.casorio.database.datasources.CategoriesDataSource;
import com.android.casorio.util.Validator;

public class CreateCategoriesFragment extends Fragment {
	
	EditText nameTxt;
	EditText coastTxt;
	
	CategoriesDataSource dataSource;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		dataSource = new CategoriesDataSource(getActivity());
		
		setHasOptionsMenu(true);
		
		View rootView = inflater.inflate(R.layout.category_create_layout, container, false);
		
		nameTxt = (EditText) rootView.findViewById(R.id.categories_create_name);
		coastTxt = (EditText) rootView.findViewById(R.id.categories_create_coast);
		
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
			insertCategory();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}
	
	private boolean insertCategory() {
		
		if (!Validator.hasText(getActivity(), nameTxt) || !Validator.isNumber(getActivity(), coastTxt, true))
		{
			return false;
		}

		
		dataSource.open();
		dataSource.createCategory(nameTxt.getText().toString(), Long.parseLong(coastTxt.getText().toString()));
		dataSource.close();

		Toast.makeText(getActivity(), "Inserted", Toast.LENGTH_SHORT).show();
		return true;
		
	}


}
