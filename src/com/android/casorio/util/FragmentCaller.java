package com.android.casorio.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;

import com.android.casorio.R;
import com.android.casorio.guest.InsertGuestActivity;
import com.android.casorio.home.HomeActivity;
import com.android.casorio.tasks.CreateTaskActivity;
import com.android.casorio.tasks.TaskListFragment;

public class FragmentCaller {

	public static void callInsertGuestActivity(Activity activity) {
		Intent callingItent = new Intent(activity, InsertGuestActivity.class);
		activity.startActivity(callingItent);
	}
	
	public static void callCreateTaskActivity(Activity activity) {
		Intent callingItent = new Intent(activity, CreateTaskActivity.class);
		activity.startActivity(callingItent);
	}
	
	public static void callHomeActivity(Activity activity) {
		Intent callingItent = new Intent(activity, HomeActivity.class);
		callingItent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		activity.startActivity(callingItent);
	}
	
	public static void callTaskListActivity(Activity activity) {
		Intent callingItent = new Intent(activity, TaskListFragment.class);
		activity.startActivity(callingItent);
	}

	public static void callFragment(Activity activity, Fragment destiny){
		FragmentManager manager = activity.getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.content_frame, destiny).commit();

	}


}
