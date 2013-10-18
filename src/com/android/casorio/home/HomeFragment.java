package com.android.casorio.home;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.casorio.R;
import com.android.casorio.settings.SettingsFragment;
import com.android.casorio.util.FragmentCaller;

public class HomeFragment extends Fragment {

	TextView countdown = null;
	TextView totalValueTxt = null;
	TextView eventNameTxt = null;

	ImageView profileImg = null;
	ProgressBar taskProgressBar;

	private static int RESULT_LOAD_IMAGE = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.home_activity_layout,
				container, false);

		
		taskProgressBar = (ProgressBar) rootView.findViewById(R.id.home_taskProgressBar);
		taskProgressBar.setProgress(50);
		countdown = (TextView) rootView.findViewById(R.id.home_countdown);
		eventNameTxt = (TextView) rootView
				.findViewById(R.id.home_event_nametextView);
		totalValueTxt = (TextView) rootView
				.findViewById(R.id.home_totalValueTextView);
		profileImg = (ImageView) rootView.findViewById(R.id.home_imageProfile);

		countdown.setText(getCountdown());
		countdown.setOnClickListener(new CallSettingsListener());

		eventNameTxt.setText(getEventName());
		eventNameTxt.setOnClickListener(new CallSettingsListener());

		profileImg.setOnClickListener(new ImagePickerListener());
		setImage(profileImg);

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
				returnValue = String.valueOf(days);

			} catch (ParseException e) {

				e.printStackTrace();
			}

		} else {
			returnValue = getString(R.string.home_count_default);
		}

		return returnValue;
	}

	private String getTotalValue() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this.getActivity());
		String returnValue = preferences.getString(
				getString(R.string.key_budget), "");

		return returnValue;
	}

	private String getEventName() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this.getActivity());
		String returnValue = preferences.getString(
				getString(R.string.key_evt_name),
				getString(R.string.home_event_name_default));

		return returnValue;
	}

	private void setImage(ImageView imageViewer) {

		String imgPath = getImagePath();

		if (!TextUtils.isEmpty(imgPath)) {
			imageViewer.setImageBitmap(decodeSampledBitmapFromFile(imgPath, 90,
					90));

		}

	}

	private String getImagePath() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this.getActivity());
		String returnValue = preferences.getString(
				getString(R.string.key_image), "");

		return returnValue;
	}

	private void setImagePath(String path) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this.getActivity());
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(getString(R.string.key_image), path);
		editor.commit();
	}

	class CallSettingsListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			FragmentCaller.callFragment(getActivity(), new SettingsFragment());
		}

	}

	class ImagePickerListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent i = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

			startActivityForResult(i, RESULT_LOAD_IMAGE);
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		getActivity();
		if (requestCode == RESULT_LOAD_IMAGE
				&& resultCode == Activity.RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getActivity().getContentResolver().query(
					selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			setImagePath(picturePath);
			profileImg.setImageBitmap(decodeSampledBitmapFromFile(picturePath,
					90, 90));

		}

	}

	public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth,
			int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}

}
