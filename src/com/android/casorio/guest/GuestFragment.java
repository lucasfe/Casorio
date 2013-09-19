package com.android.casorio.guest;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.casorio.R;
import com.android.casorio.database.GuestDataSource;

public class GuestFragment extends ListFragment {
	
	
	private ListView guestListView;
	private TextView guestCounterTxtView;
	GuestAdapter adapter;
	GuestDataSource dataSource;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		View rootView = inflater.inflate(
				R.layout.fragment_invite_list_dummy, container, false);
		
		Context context = getActivity();
		guestListView = (ListView) rootView.findViewById(android.R.id.list);
		guestCounterTxtView = (TextView)rootView.findViewById(R.id.guest_list_count_txt);
		
		
		GuestDataSource dataSource = new GuestDataSource(context);
		dataSource.open();
		Cursor cursor = dataSource.getAllGuestsCursor();
		if(cursor != null) {
			guestCounterTxtView.setText(getString(R.string.guest_list_counter) + " " + cursor.getCount());
		}
		
		cursor.getCount();
		adapter = new GuestAdapter(guestListView.getContext(), cursor);

		guestListView.setAdapter(adapter);
		dataSource.close();

		
		return rootView;
	}
	


}
