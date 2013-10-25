package com.android.casorio.guest;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.casorio.R;
import com.android.casorio.database.datasources.GuestDataSource;

public class GuestDetailsFragment extends Fragment {

	private TextView name = null;
	private TextView email = null;
	private TextView type = null;
	private TextView additional = null;
	private TextView confirmed = null;

	Guest guest = null;
	GuestDataSource mDataSource;

	private IGuestListener mGuestListener;

	public static final String GUEST_ID = "guestId";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setHasOptionsMenu(true);

		View returnView = inflater.inflate(R.layout.guest_details_layout,
				container, false);
		mDataSource = new GuestDataSource(getActivity());

		return returnView;
	}

	private void fillData(Guest guest) {

		if (guest != null) {

			List<String> guestTypes = Arrays.asList(getActivity()
					.getResources().getStringArray(R.array.guest_type_options));

			name = (TextView) getActivity().findViewById(
					R.id.guest_details_name);
			name.setText(guest.getName());

			email = (TextView) getActivity().findViewById(
					R.id.guest_details_email);
			email.setText(guest.getEmail());

			type = (TextView) getActivity().findViewById(
					R.id.guest_details_type);
			type.setText(guestTypes.get(guest.getGuestStatus().getValue()));

			additional = (TextView) getActivity().findViewById(
					R.id.guest_details_additional);
			additional.setText(String.valueOf(guest.getAdditinal_guests()));

			confirmed = (TextView) getActivity().findViewById(
					R.id.guest_details_status);
			confirmed.setText(guest.getGuestStatus().getStatusText(
					getActivity()));
		}

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.generic_viewer_menu, menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_call_insert:
			mGuestListener.onInsertGuest();
			break;

		case R.id.action_call_delete:
			mGuestListener.onGuestDeleted();
			break;
			
		case R.id.action_call_edit:
			mGuestListener.onGuestEdit(guest.getId());
			break;
		}

		return true;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (activity instanceof IGuestListener) {
			mGuestListener = (IGuestListener) activity;
		} else {
			throw new ClassCastException();
		}

	}

	@Override
	public void onStart() {
		super.onStart();

		Bundle args = getArguments();
		if (args != null) {
			int guestId = args.getInt(GUEST_ID);
			mDataSource.open();
			guest = mDataSource.getGuestById(guestId);
			fillData(guest);
			mDataSource.close();
		}
	}

}
