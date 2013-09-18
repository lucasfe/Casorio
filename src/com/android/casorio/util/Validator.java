package com.android.casorio.util;

import java.util.regex.Pattern;

import com.android.casorio.R;

import android.content.Context;
import android.widget.EditText;

public class Validator {

	private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PHONE_REGEX = "\\d{3}-\\d{7}";
	private static final String NUMBER_REGEX = "[0-9]+";
	

	// call this method when you need to check email validation
	public static boolean isEmailAddress(Context context, EditText editText, boolean required) {
		return isValid(context, editText, EMAIL_REGEX, context.getString(R.string.invalid_email), required);
	}

	// call this method when you need to check phone number validation
	public static boolean isPhoneNumber(Context context, EditText editText, boolean required) {
		return isValid(context, editText, PHONE_REGEX, context.getString(R.string.invalid_phone), required);
	}
	
	// call this method when you need to check email validation
	public static boolean isNumber(Context context, EditText editText, boolean required) {
		return isValid(context, editText, NUMBER_REGEX, context.getString(R.string.invalid_number), required);
	}


	// return true if the input field is valid, based on the parameter passed
	public static boolean isValid(Context context, EditText editText, String regex,
			String errMsg, boolean required) {

		String text = editText.getText().toString().trim();
		// clearing the error, if it was previously set by some other values
		editText.setError(null);

		boolean hasText = hasText(context, editText);
		
		// text required and editText is blank, so return false
		if (required && !hasText){
			return false;
		}
		// pattern doesn't match so returning false
		if (hasText && !Pattern.matches(regex, text)) {
			editText.setError(errMsg);
			return false;
		}
		;
		return true;
	}

	// check the input field has any text or not
	// return true if it contains text otherwise false
	public static boolean hasText(Context context, EditText editText) {

		String text = editText.getText().toString().trim();
		editText.setError(null);

		// length 0 means there is no text
		if (text.length() == 0) {
			editText.setError(context.getString(R.string.required_field));
			return false;
		}

		return true;
	}
}
