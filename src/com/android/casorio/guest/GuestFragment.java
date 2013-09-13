package com.android.casorio.guest;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.casorio.R;
import com.android.casorio.database.GuestDataSource;

public class GuestFragment extends ListFragment {
	
	
	private ListView guestListView;
	
	GuestDataSource dataSource;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		View rootView = inflater.inflate(
				R.layout.fragment_invite_list_dummy, container, false);
		
		Context context = getActivity();
		guestListView = (ListView) rootView.findViewById(android.R.id.list);
		
		
		GuestDataSource dataSource = new GuestDataSource(context);
		dataSource.open();
		List<Guest> allGuests = dataSource.getAllGuests();
		Guest[] guestArray = allGuests.toArray(new Guest[allGuests.size()]);
		
		GuestAdapter adapter = new GuestAdapter(getActivity(), R.layout.guest_list_item, guestArray);
		guestListView.setAdapter(adapter);
		
		return rootView;
	}


}
