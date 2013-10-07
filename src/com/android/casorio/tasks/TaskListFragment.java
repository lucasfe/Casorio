package com.android.casorio.tasks;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.casorio.R;
import com.android.casorio.database.Task;
import com.android.casorio.database.TaskDataSource;
import com.android.casorio.util.ActivityStarter;

public class TaskListFragment extends Fragment implements Callback {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.task_list_layout,
				container, false);
		setHasOptionsMenu(true);

		TaskDataSource source = new TaskDataSource(getActivity());
		
		source.open();
		
		List<Task> allTasks = source.getAllTasks();
		
		source.close();
		
		return rootView;
	}
	
	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		MenuInflater inflater = mode.getMenuInflater();
		inflater.inflate(R.menu.task_list_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
        
        return true;
	}


	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {

	    case R.id.action_call_create_task:
	    	ActivityStarter.callCreateTaskActivity(getActivity());
	      break;
	    }

	    return true;
	  }

	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		// TODO Auto-generated method stub
		return false;
	} 
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.task_list_menu, menu);
		
	}



}
