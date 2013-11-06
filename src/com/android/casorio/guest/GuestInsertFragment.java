package com.android.casorio.guest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.lucasfe.casorio.R;
import com.android.casorio.database.datasources.GuestDataSource;
import com.android.casorio.guest.Guest.GuestStatus;
import com.android.casorio.guest.Guest.GuestType;
import com.android.casorio.util.Validator;

public class GuestInsertFragment extends Fragment {

	public static final String GUEST_ID = "guestId";

	GuestDataSource dataSource;
	Guest guest = null;

	EditText name;
	EditText email;
	EditText additional;
	RadioGroup statusGroup;
	Spinner spinner;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setHasOptionsMenu(true);

		View returnView = inflater.inflate(R.layout.guest_create_layout,
				container, false);

		dataSource = new GuestDataSource(getActivity());

		name = (EditText) returnView.findViewById(R.id.nameText);
		email = (EditText) returnView.findViewById(R.id.emailText);
		additional = (EditText) returnView.findViewById(R.id.additionalText);
		statusGroup = (RadioGroup) returnView
				.findViewById(R.id.guest_radio_group_status);
		spinner = (Spinner) returnView.findViewById(R.id.type_spinner);

		return returnView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.guest_insert_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_insert_guest:
			insertOrUpdateGuest();
			break;
		}

		return true;
	}

	@Override
	public void onStart() {
		super.onStart();
		guest = null;
		Bundle args = getArguments();
		if (args != null) {
			long guestId = args.getLong(GUEST_ID);
			dataSource.open();
			guest = dataSource.getGuestById(guestId);
			fillUI(guest);
			dataSource.close();
		}
	}

	private void fillUI(Guest guest) {
		name.setText(guest.getName());
		email.setText(guest.getEmail());
		additional.setText(String.valueOf(guest.getAdditinal_guests()));
		setRadioGroup(guest.getGuestStatus());
		spinner.setSelection(guest.getType());

	}

	private boolean insertGuestAction() {

		GuestStatus status = getStatusFromRadioGroup(statusGroup);
		GuestType type = getTypeFromSpinner(spinner);

		if (!Validator.hasText(getActivity(), name)
				|| !Validator.isNumber(getActivity(), additional, true)) {
			return false;
		}
		dataSource.open();
		Guest returnedGuest = dataSource
				.createGuest(name.getText().toString(), email.getText()
						.toString(), Integer.parseInt(additional.getText()
						.toString()), type, status);
		dataSource.close();
		boolean result = returnedGuest != null ? true : false;
		if (result) {
			Toast.makeText(
					getActivity(),
					getActivity().getResources().getString(
							R.string.guest_inserted_confirmation_message),
					Toast.LENGTH_SHORT).show();
		}
		return result;

	}
	
	
	private void updateGuestAction(Guest guest) {
		dataSource.open();		
		if(updateGuestFields(guest)) {
			dataSource.updateGuest(guest);
			Toast.makeText(
					getActivity(),
					getActivity().getResources().getString(
							R.string.guest_updated_confirmation_message),
					Toast.LENGTH_SHORT).show();

		}
		dataSource.close();
		
	}
	
	
	private boolean updateGuestFields(Guest guest)
	{
		GuestStatus status = getStatusFromRadioGroup(statusGroup);
		GuestType type = getTypeFromSpinner(spinner);
		
		if (!Validator.hasText(getActivity(), name)
				|| !Validator.isNumber(getActivity(), additional, true)) {
			return false;
		}
		
		guest.setName(name.getText().toString());
		guest.setEmail(email.getText().toString());
		guest.setType(type.getValue());
		guest.setStatus(status.getValue());
		guest.setAdditinal_guests(Integer.parseInt(additional.getText().toString()));


		return true;
	}
	
	
	private void insertOrUpdateGuest() {
		if (guest == null) { 
			insertGuestAction();	
		}
		else {
			updateGuestAction(guest);
		}
	}

	private GuestStatus getStatusFromRadioGroup(RadioGroup radioGroup) {
		GuestStatus result = GuestStatus.PENDING;

		int selected = radioGroup.getCheckedRadioButtonId();

		switch (selected) {
		case R.id.guest_radio_yes:
			result = Guest.GuestStatus.CONFIRMED;
			break;

		case R.id.guest_radio_maybe:
			result = Guest.GuestStatus.PENDING;
			break;

		case R.id.guest_radio_no:
			result = Guest.GuestStatus.NOT_GOING;
			break;

		default:
			break;
		}

		return result;

	}

	private void setRadioGroup(GuestStatus status) {

		switch (status) {
		case CONFIRMED:
			statusGroup.check(R.id.guest_radio_yes);
			break;

		case PENDING:
			statusGroup.check(R.id.guest_radio_maybe);
			break;

		case NOT_GOING:
			statusGroup.check(R.id.guest_radio_no);
			break;

		default:
			break;
		}

	}

	private GuestType getTypeFromSpinner(Spinner spinner) {

		return GuestType.values()[spinner.getSelectedItemPosition()];

	}

}
