package com.android.casorio.tasks;

import java.text.NumberFormat;
import java.util.Date;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.casorio.R;
import com.android.casorio.categories.Category;
import com.android.casorio.database.datasources.CategoriesDataSource;
import com.android.casorio.database.datasources.TaskDataSource;

public class TaskDetailsFragment extends Fragment {
	
	public static final String TASK_ID = "taskId";
	
	private ITaskListener mTaskListener;
	private TaskDataSource mDataSource;
	private CategoriesDataSource mCategoriesDataSource;
	private Task task;
	
	private TextView title;
	private TextView categoryTxtView;
	private TextView date;
	private TextView coast;
	private TextView note;
	
	NumberFormat format;


	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View rootView = inflater.inflate(R.layout.task_details_layout, container, false);
		setHasOptionsMenu(true);
		mDataSource = new TaskDataSource(getActivity());
		mCategoriesDataSource = new CategoriesDataSource(getActivity());
		
		format = NumberFormat.getCurrencyInstance();

		
		//bind fields to layout
		title = (TextView) rootView.findViewById(R.id.task_details_titleTxtView);
		categoryTxtView = (TextView) rootView.findViewById(R.id.task_details_category_TxtView);
		date = (TextView) rootView.findViewById(R.id.task_details_date_txtView);
		coast  = (TextView) rootView.findViewById(R.id.task_details_coast_txtView);
		note = (TextView) rootView.findViewById(R.id.task_details_note_txtView);
		return rootView;
	}
	
	private void fillData(Task task) {

		if (task != null) {

			mCategoriesDataSource.open();
			Category category = mCategoriesDataSource.getCategoryById(task.getCategoryId());
			mCategoriesDataSource.close();
			
			title.setText(task.getName());
			categoryTxtView.setText(category.getName());
			
			date.setText(android.text.format.DateFormat.getDateFormat(getActivity()).format(new Date(task.getDueDate())));
			coast.setText(format.format(task.getCoast()));
			note.setText(task.getNote());

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
