package com.android.casorio.tasks;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.casorio.R;
import com.android.casorio.database.datasources.TaskDataSource;

public class TaskDetailsFragment extends Fragment {
	
	public static final String TASK_ID = "taskId";
	
	private ITaskListener mTaskListener;
	private TaskDataSource mDataSource;
	private Task task;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View rootView = inflater.inflate(R.layout.task_details_layout, container, false);
		setHasOptionsMenu(true);
		mDataSource = new TaskDataSource(getActivity());
		
		return rootView;
	}
	
	private void fillData(Task task) {

		if (task != null) {

		}

	}

	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.generic_viewer_menu, menu);

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_call_insert:
			mTaskListener.OnCreateTask();
			break;

		case R.id.action_call_delete:
			mTaskListener.OnTaskDeleted(task.getId());
			break;
			
		case R.id.action_call_edit:
			mTaskListener.OnTaskUpdated(task.getId());
			break;
		}

		return true;
	}
	
	@Override
	public void onStart() {
		super.onStart();

		Bundle args = getArguments();
		if (args != null) {
			long taskId = args.getLong(TASK_ID);
			mDataSource.open();
			task = mDataSource.getTaskById(taskId);
			fillData(task);
			mDataSource.close();
		}
	}
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (activity instanceof ITaskListener) {
			mTaskListener = (ITaskListener) activity;
		} else {
			throw new ClassCastException();
		}

	}


}
