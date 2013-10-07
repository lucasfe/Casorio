package com.android.casorio.util;

import android.app.Activity;
import android.content.Intent;

import com.android.casorio.HomeActivity;
import com.android.casorio.guest.InsertGuestActivity;
import com.android.casorio.tasks.TaskListFragment;

public class ActivityStarter {

	public static void callInsertGuestActivity(Activity activity) {
		Intent callingItent = new Intent(activity, InsertGuestActivity.class);
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


}
