package com.android.casorio.tasks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.casorio.R;
import com.android.casorio.categories.Category;
import com.android.casorio.database.datasources.CategoriesDataSource;
import com.android.casorio.database.datasources.TaskDataSource;
import com.android.casorio.util.Validator;

public class CreateTaskFragment extends Fragment {

	public static final String TASK_ID = "taskId";

	TaskDataSource taskDataSource;
	CategoriesDataSource categoriesDataSource;

	Task task;

	EditText titleTxt;
	DatePicker dueDatePicker;
	EditText coast;
	EditText note;
	Spinner categoriesSpinner;
	
	RadioGroup radioGroup;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(R.layout.task_create_layout,
				container, false);

		taskDataSource = new TaskDataSource(getActivity());
		categoriesDataSource = new CategoriesDataSource(getActivity());

		setHasOptionsMenu(true);

		titleTxt = (EditText) rootView.findViewById(R.id.taskNameText);
		dueDatePicker = (DatePicker) rootView
				.findViewById(R.id.task_due_date_picker);
		coast = (EditText) rootView.findViewById(R.id.task_coast_text);
		note = (EditText) rootView.findViewById(R.id.task_note_text);
		categoriesSpinner = (Spinner) rootView
				.findViewById(R.id.task_category_spinner);
		radioGroup = (RadioGroup) rootView.findViewById(R.id.task_create_radio_group_status);
		

		categoriesDataSource.open();
		List<Category> categories = categoriesDataSource.getAllCategories();
		categoriesDataSource.close();
		List<String> categoriesArray = new ArrayList<String>();

		for (Category cat : categories) {
			categoriesArray.add(cat.getName());
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_dropdown_item, categoriesArray);
		categoriesSpinner.setAdapter(adapter);

		return rootView;

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.clear();
		inflater.inflate(R.menu.task_create_menu, menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_call_create_task:
			insertOrUpdateTask();
			break;
		}
		return true;
	}

	@Override
	public void onStart() {
		super.onStart();
		task = null;
		Bundle args = getArguments();
		if (args != null) {
			long taskid = args.getLong(TASK_ID);
			taskDataSource.open();
			task = taskDataSource.getTaskById(taskid);
			fillUI(task);
			taskDataSource.close();
		}
	}
	
	
	private void fillUI(Task task) {
		//dueDatePicker;
		
		Long temp =  Long.valueOf((task.getCategory_id()));	
		
		titleTxt.setText(task.getName());
		categoriesSpinner.setSelection(temp.intValue());
		coast.setText(String.valueOf(task.getCoast()));
		note.setText(task.getNote());
		
		if (task.isCompleted()) { 
			radioGroup.check(R.id.task_create_radio_yes);	
		}
		else {
			radioGroup.check(R.id.task_create_radio_no);
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(task.getDueDate());
		dueDatePicker.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
	}


	private void insertOrUpdateTask() {
		if (task == null) { 
			insertTaskAction();	
		}
		else {
			updateTaskAction(task);
		}
	}

	
	private boolean updateTaskAction(Task task) {
		if (!Validator.hasText(getActivity(), titleTxt)
				|| !Validator.isNumber(getActivity(), coast, true)
				|| task == null) {
			return false;
		}

		Calendar selected = new GregorianCalendar(dueDatePicker.getYear(),
				dueDatePicker.getMonth(), dueDatePicker.getDayOfMonth());
		
		boolean completed = radioGroup.getCheckedRadioButtonId() == R.id.task_create_radio_yes;
		
		task.setDueDate(selected.getTimeInMillis());
		task.setName(titleTxt.getText().toString());
		task.setCategory_id(categoriesSpinner.getSelectedItemId());
		task.setCoast(Integer.parseInt(coast.getText().toString()));
		task.setNote(note.getText().toString());
		task.setCompleted(completed);
		taskDataSource.open();

		taskDataSource.updateGuest(task);
		taskDataSource.close();
		Toast.makeText(getActivity(), "Task Created", Toast.LENGTH_SHORT).show();
		return true;
	}
	
	@SuppressLint("SimpleDateFormat")
	private boolean insertTaskAction() {

		if (!Validator.hasText(getActivity(), titleTxt)
				|| !Validator.isNumber(getActivity(), coast, true)) {
			return false;
		}

		Calendar selected = new GregorianCalendar(dueDatePicker.getYear(),
				dueDatePicker.getMonth(), dueDatePicker.getDayOfMonth());
		long dueDateString = selected.getTimeInMillis();
		int status = 0;
		
		if (radioGroup.getCheckedRadioButtonId() == R.id.task_create_radio_yes) {
			status = 1;
		}
		taskDataSource.open();

		taskDataSource.createTask(titleTxt.getText().toString(),
				categoriesSpinner.getSelectedItemId(), Long.parseLong(coast
						.getText().toString()), dueDateString, note.getText()
						.toString(), status);
		taskDataSource.close();
		Toast.makeText(getActivity(), "Task Created", Toast.LENGTH_SHORT).show();
		return true;
	}

}
