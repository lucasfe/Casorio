package com.android.casorio.guest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.casorio.R;
import com.android.casorio.R.id;
import com.android.casorio.R.layout;

public class GuestDetailsActivity extends Activity {

	private TextView name = null;
	Guest guest = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.guest_details_layout);

		Intent intent = getIntent();
		Bundle items = intent.getExtras();

		guest = (Guest) items.get("Guest");
		
		fillData(guest);

	}

	private void fillData(Guest guest) {

		if (guest != null) {
			name = (TextView) findViewById(R.id.guest_details_name);
			name.setText(guest.getName());
		}

	}

}
