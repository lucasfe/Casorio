package com.android.casorio.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.lucasfe.casorio.R;

public class FragmentCaller {
	
	public static void callFragment(Activity activity, Fragment destiny){
		FragmentManager manager = activity.getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.content_frame, destiny);
		transaction.addToBackStack(null);
		transaction.commit();
		
	}
	
	public static void callFragment(Activity activity, Fragment destiny, String tag){
		FragmentManager manager = activity.getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.content_frame, destiny);
		transaction.addToBackStack(tag);
		transaction.commit();
		
	}



}
