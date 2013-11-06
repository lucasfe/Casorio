package com.android.casorio.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.lucasfe.casorio.R;

public class SettingsFragment extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.settings);
	}

}
