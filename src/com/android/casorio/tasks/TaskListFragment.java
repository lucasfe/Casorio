package com.android.casorio.tasks;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.casorio.R;
import com.android.casorio.database.datasources.TaskDataSource;
import com.android.casorio.util.FragmentCaller;

public class TaskListFragment extends Fragment implements Callback {
	
	
	public static final String CATEGORY_ID = "categoryId";
	
	private ListView taskListView;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.task_list_layout,
				container, false);
		setHasOptionsMenu(true);

		TaskDataSource source = new TaskDataSource(getActivity());
		
		taskListView = (ListView) rootView.findViewById(R.id.taskList);
		
		source.open();
		
		Cursor allTasksCursor = source.getAllTasksCursor();
		
		TaskListAdapter adapter = new TaskListAdapter(getActivity(), allTasksCursor);
		
		taskListView.setAdapter(adapter);
		
		source.close();

		
		return rootView;
	}
	
	
	private void updateListSelection(int categoryId) {

	}
	
    @Override
    public void onStart() {
        super.onStart();
        
        Bundle args = getArguments();
        if (args != null) {
        	int catId = args.getInt(CATEGORY_ID);
        }
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
	    	FragmentCaller.callCreateTaskActivity(getActivity());
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

