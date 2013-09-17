package com.android.casorio;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.casorio.database.GuestDataSource;
import com.android.casorio.guest.Guest;

public class InsertGuestActivity extends Activity {
	
	
	GuestDataSource dataSource;
	
	EditText name;
	EditText phone;
	EditText email;
	EditText adress;
	EditText additional;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert_guest_layout);
		
		dataSource = new GuestDataSource(getApplicationContext());
		
		name = (EditText) findViewById(R.id.nameText);
		phone = (EditText) findViewById(R.id.phoneText);
		email = (EditText) findViewById(R.id.emailText);
		adress = (EditText) findViewById(R.id.adressText);
		additional = (EditText) findViewById(R.id.additionalText);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_guest_list, menu);
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.action_insert_guest:
	    	insertGuestAction(this);
	      break;
	    }

	    return true;
	  } 

	
	private boolean insertGuestAction(Context context) {
		dataSource.open();
		Guest returnedGuest = dataSource.createGuest(name.getText().toString(), name.getText().toString(), email.getText().toString(),
				phone.getText().toString(), Integer.parseInt(additional.getText().toString()), 0);
		dataSource.close();
		boolean result = returnedGuest != null ? true : false;
		if(result) { 
			Toast.makeText(context, context.getResources().getString(R.string.guest_inserted_confirmation_message), Toast.LENGTH_LONG).show();
		}
		return result;
		
	}


}
