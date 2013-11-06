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
import android.widget.TextView;

import com.lucasfe.casorio.R;
import com.android.casorio.categories.Category;
import com.android.casorio.categories.IOnCategorySelectedListener;
import com.android.casorio.database.datasources.CategoriesDataSource;
import com.android.casorio.database.datasources.TaskDataSource;

public class TaskListFragment extends Fragment {

	public static final String CATEGORY_ID = "categoryId";
	private ITaskListener mTaskListener;
	private IOnCategorySelectedListener mCategoryListener;

	private ListView taskListView;

	private TextView headerTitleTxtView;
	private MenuItem editMenuItem;

	List<Task> allTasksCursor;
	TaskDataSource source;
	CategoriesDataSource mCategoryDataSource;

	private long categoryId = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.task_list_layout, container,
				false);
		setHasOptionsMenu(true);

		source = new TaskDataSource(getActivity());
		mCategoryDataSource = new CategoriesDataSource(getActivity());

		taskListView = (ListView) rootView.findViewById(R.id.taskList);
		headerTitleTxtView = (TextView) rootView
				.findViewById(R.id.task_list_header_title);

		source.open();

		allTasksCursor = source.getAllTasks();

		final TaskListAdapter adapter = new TaskListAdapter(allTasksCursor,
				getActivity());

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

		// update header with category name

		mCategoryDataSource.open();
		Category cat = mCategoryDataSource.getCategoryById(categoryId);
		mCategoryDataSource.close();

		headerTitleTxtView.setText(cat.getName());

		TaskListAdapter adapter = (TaskListAdapter) taskListView.getAdapter();
		source.open();
		allTasksCursor = source.getTasksByCategory(categoryId);
		adapter.setTaskList(allTasksCursor);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onStart() {
		super.onStart();

		headerTitleTxtView.setText(getActivity().getString(
				R.string.task_list_header_title));

		Bundle args = getArguments();
		if (args != null) {
			editMenuItem.setVisible(true);
			categoryId = args.getLong(CATEGORY_ID);
			updateListSelection(categoryId);
		}
		getActivity().invalidateOptionsMenu();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_call_create_task:
			mTaskListener.OnCreateTask();
			break;

		case R.id.action_call_edit:
			mCategoryListener.onCategoryUpdated(categoryId);
			break;

		}

		return true;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.task_list_menu, menu);
		editMenuItem = menu.findItem(R.id.action_call_edit);
		editMenuItem.setVisible(categoryId != 0);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		if ((activity instanceof ITaskListener)
				&& activity instanceof IOnCategorySelectedListener) {
			mTaskListener = (ITaskListener) activity;
			mCategoryListener = (IOnCategorySelectedListener) activity;
		}

		else {
			throw new ClassCastException(activity.toString()
					+ "must implement ITaskListener");
		}
	}

}
