package com.android.casorio.tasks;

import com.android.casorio.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TaskDetailsFragment extends Fragment {
	
	public static final String TASK_ID = "taskId";
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View rootView = inflater.inflate(R.layout.task_details_layout, container, false);
		
		return rootView;
	}

}
