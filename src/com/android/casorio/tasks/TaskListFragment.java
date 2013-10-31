package com.android.casorio.tasks;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.casorio.R;
import com.android.casorio.database.datasources.TaskDataSource;

public class TaskListFragment extends Fragment {
	
	
	public static final String CATEGORY_ID = "categoryId";
	private ITaskListener mTaskListener;
	
	private ListView taskListView;
	
	List<Task> allTasksCursor;
	TaskDataSource source;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.task_list_layout,
				container, false);
		setHasOptionsMenu(true);

		source = new TaskDataSource(getActivity());
		
		taskListView = (ListView) rootView.findViewById(R.id.taskList);
		
		
		source.open();
		
		allTasksCursor = source.getAllTasks();
		
		final TaskListAdapter adapter = new TaskListAdapter(allTasksCursor, getActivity());
	
		
		taskListView.setAdapter(adapter);
		
		taskListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mTaskListener.OnTaskSelected(adapter.getItemId(position));
			}
		});

		
		source.close();
		return rootView;
	}
	
	
	private void updateListSelection(long categoryId) {
		TaskListAdapter adapter = (TaskListAdapter) taskListView.getAdapter();
		source.open();
		allTasksCursor = source.getTasksByCategory(categoryId);
		adapter.setTaskList(allTasksCursor);
		adapter.notifyDataSetChanged();
	}
	
    @Override
    public void onStart() {
        super.onStart();
        
        Bundle args = getArguments();
        if (args != null) {
        	long catId = args.getLong(CATEGORY_ID);
        	updateListSelection(catId);
        }
    }

	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {

	    case R.id.action_call_create_task:
	    	mTaskListener.OnCreateTask();
	      break;
	    }

	    return true;
	  }

	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.task_list_menu, menu);
		
	}

	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		if ((activity instanceof ITaskListener)) {
			mTaskListener = (ITaskListener) activity;

		} else {
			throw new ClassCastException(activity.toString()
					+ "must implement ITaskListener");
		}
	}


}

