package com.android.casorio;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class InsertGuestActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert_guest_layout);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_guest_list, menu);
		return true;
	}


}
