package com.android.casorio.guest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.casorio.R;
import com.android.casorio.database.GuestDataSource;
import com.android.casorio.guest.Guest.GuestStatus;
import com.android.casorio.guest.Guest.GuestType;
import com.android.casorio.util.ActivityStarter;
import com.android.casorio.util.Validator;

public class InsertGuestActivity extends Activity {
	
	
	GuestDataSource dataSource;
	
	EditText name;
	EditText email;
	EditText additional;
	RadioGroup statusGroup;
	Spinner spinner;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert_guest_layout);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);

		
		dataSource = new GuestDataSource(getApplicationContext());
		
		name = (EditText) findViewById(R.id.nameText);
		email = (EditText) findViewById(R.id.emailText);
		additional = (EditText) findViewById(R.id.additionalText);
		statusGroup = (RadioGroup) findViewById(R.id.guest_radio_group_status);
		spinner = (Spinner) findViewById(R.id.type_spinner);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.guest_insert_menu, menu);
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
		
	    case android.R.id.home:
			ActivityStarter.callHomeActivity(this);
			break;
	    
	    case R.id.action_insert_guest:
	    	insertGuestAction(this);
	      break;
	    }

	    return true;
	  } 

	
	private boolean insertGuestAction(Context context) {
		
		GuestStatus status = getStatusFromRadioGroup(statusGroup);
		GuestType type = getTypeFromSpinner(spinner);
		
		if(!Validator.hasText(this, name) || !Validator.isNumber(this, additional, true))
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
