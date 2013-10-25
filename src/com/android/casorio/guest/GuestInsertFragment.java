package com.android.casorio.guest;

import android.app.Fragment;
import android.content.Context;
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

import com.android.casorio.R;
import com.android.casorio.database.datasources.GuestDataSource;
import com.android.casorio.guest.Guest.GuestStatus;
import com.android.casorio.guest.Guest.GuestType;
import com.android.casorio.util.Validator;

public class GuestInsertFragment extends Fragment {
	
	
	GuestDataSource dataSource;
	
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

		View returnView = inflater.inflate(R.layout.insert_guest_layout, container, false);
		
		dataSource = new GuestDataSource(getActivity());
		
		name = (EditText) returnView.findViewById(R.id.nameText);
		email = (EditText) returnView.findViewById(R.id.emailText);
		additional = (EditText) returnView.findViewById(R.id.additionalText);
		statusGroup = (RadioGroup) returnView.findViewById(R.id.guest_radio_group_status);
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
	    	insertGuestAction(getActivity());
	      break;
	    }

	    return true;
	  } 

	
	private boolean insertGuestAction(Context context) {
		
		GuestStatus status = getStatusFromRadioGroup(statusGroup);
		GuestType type = getTypeFromSpinner(spinner);
		
		if(!Validator.hasText(getActivity(), name) || !Validator.isNumber(getActivity(), additional, true))
		{
			return false;
		}
		dataSource.open();
		Guest returnedGuest = dataSource.createGuest(name.getText().toString(), email.getText().toString(), Integer.parseInt(additional.getText().toString()), type, status);
		dataSource.close();
		boolean result = returnedGuest != null ? true : false;
		if(result) { 
			Toast.makeText(context, context.getResources().getString(R.string.guest_inserted_confirmation_message), Toast.LENGTH_LONG).show();
		}
		return result;
		
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
	
	private GuestType getTypeFromSpinner(Spinner spinner) {
	GuestType result = GuestType.FRIEND;
	
	int selected = spinner.getSelectedItemPosition();
	
	switch (selected) {
	case 0:
		result = Guest.GuestType.FRIEND;
		break;

	case 1:
		result = Guest.GuestType.GODFATHER;
		break;
		
	case 2:
		result = Guest.GuestType.RElATIVE;
		break;	

	default:
		break;
	}
	
	return result;
		
		
	}


}
