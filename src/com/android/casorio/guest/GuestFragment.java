package com.android.casorio.guest;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.casorio.R;
import com.android.casorio.database.datasources.GuestDataSource;
import com.android.casorio.util.FragmentCaller;

public class GuestFragment extends ListFragment implements  Callback {

	private ListView guestListView;
	private TextView guestCounterTxtView;
	GuestAdapter adapter;
	GuestDataSource dataSource;
	
	private IGuestListener mOnGuestSelectedListener;

	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		View rootView = inflater.inflate(R.layout.fragment_invite_list_dummy,
				container, false);

		Context context = getActivity();
		guestListView = (ListView) rootView.findViewById(android.R.id.list);
		guestCounterTxtView = (TextView) rootView
				.findViewById(R.id.guest_list_count_txt);

		GuestDataSource dataSource = new GuestDataSource(context);
		dataSource.open();
		Cursor cursor = dataSource.getAllGuestsCursor();
		if (cursor != null) {
			guestCounterTxtView.setText(getString(R.string.guest_list_counter)
					+ " " + cursor.getCount());
		}

		cursor.getCount();
		adapter = new GuestAdapter(guestListView.getContext(), cursor);

		guestListView.setAdapter(adapter);
		dataSource.close();

		return rootView;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		if (activity instanceof IGuestListener) {
			mOnGuestSelectedListener = (IGuestListener) activity;
		} 
		else {
			throw new ClassCastException();
		}
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		case R.id.action_add_guest:
			mOnGuestSelectedListener.onInsertGuest();
			break;
		}

		return true;
	}



	public void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);
		
		mOnGuestSelectedListener.onGuestSelected(position + 1);

	}


	@Override
	public boolean onActionItemClicked(ActionMode arg0, MenuItem arg1) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		MenuInflater inflater = mode.getMenuInflater();
		inflater.inflate(R.menu.guest_list_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
        
        return true;
	}



	@Override
	public void onDestroyActionMode(ActionMode mode) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.guest_list_menu, menu);
		
	}


	
}
