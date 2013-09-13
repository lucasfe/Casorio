package com.android.casorio;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.android.casorio.database.GuestDataSource;

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
	    	insertGuestAction();
	      break;
	    }

	    return true;
	  } 

	
	private boolean insertGuestAction() {
		dataSource.open();
		dataSource.createGuest(name.getText().toString(), name.getText().toString(), email.getText().toString(),
				phone.getText().toString(), Integer.parseInt(additional.getText().toString()), 0);
		dataSource.close();
		return false;
		
	}


}
