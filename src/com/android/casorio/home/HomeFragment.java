package com.android.casorio.home;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.casorio.R;

public class HomeFragment extends Fragment {

	TextView countdown = null;
	TextView totalValueTxt = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.home_activity_layout,
				container, false);

		countdown = (TextView) rootView.findViewById(R.id.home_countdown);
		totalValueTxt = (TextView) rootView
				.findViewById(R.id.home_totalValueTextView);

		countdown.setText(getCountdown());
		totalValueTxt.setText(getTotalValue());
		return rootView;

	}

	@SuppressLint("SimpleDateFormat")
	private String getCountdown() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this.getActivity());
		String returnValue = preferences.getString("weeding_date", "");

		int days = 0;
		if (!TextUtils.isEmpty(returnValue)) {

			try {
				Date date = new SimpleDateFormat("yyyy.MM.dd")
						.parse(returnValue);
				days = Days.daysBetween(new DateTime(new Date()),
						new DateTime(date)).getDays();
				returnValue = "Faltam " + days + " dias para seu casamento";

			} catch (ParseException e) {

				e.printStackTrace();
			}

		} else {
			returnValue = "Please select a date";
		}

		return returnValue;
	}

	private String getTotalValue() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this.getActivity());
		String returnValue = preferences.getString(getString(R.string.key_budget), "");

		return returnValue;
	}
	
}
