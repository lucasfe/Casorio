package com.android.casorio.tasks;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.casorio.R;
import com.android.casorio.database.Task;
import com.android.casorio.database.TaskDataSource;

public class TaskListFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.task_list_layout,
				container, false);
		
		TaskDataSource source = new TaskDataSource(getActivity());
		
		source.open();
		
		List<Task> allTasks = source.getAllTasks();
		
		source.close();
		
		return rootView;
	}

}
