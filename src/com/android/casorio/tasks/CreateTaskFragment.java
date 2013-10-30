package com.android.casorio.tasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.casorio.R;
import com.android.casorio.categories.Category;
import com.android.casorio.database.datasources.CategoriesDataSource;
import com.android.casorio.database.datasources.TaskDataSource;
import com.android.casorio.util.Validator;

public class CreateTaskFragment extends Fragment {
	
	
	TaskDataSource taskDataSource;
	CategoriesDataSource categoriesDataSource;
	
	
	EditText titleTxt;
	DatePicker dueDatePicker;
	EditText coast;
	EditText note;
	TextView reminderTxtView;
	Spinner categoriesSpinner;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(R.layout.task_create_layout, container, false);
		
		taskDataSource = new TaskDataSource(getActivity()); 
		categoriesDataSource = new CategoriesDataSource(getActivity());
		
		
		setHasOptionsMenu(true);
		
		titleTxt = (EditText) rootView.findViewById(R.id.taskNameText);
		dueDatePicker = (DatePicker) rootView.findViewById(R.id.task_due_date_picker);
		coast = (EditText) rootView.findViewById(R.id.task_coast_text);
		note = (EditText) rootView.findViewById(R.id.task_note_text);
		reminderTxtView = (TextView) rootView.findViewById(R.id.task_reminder_txt_view);
		categoriesSpinner = (Spinner) rootView.findViewById(R.id.task_category_spinner);
		
		categoriesDataSource.open();
		List<Category> categories = categoriesDataSource.getAllCategories();
		categoriesDataSource.close();
		List<String> categoriesArray = new ArrayList<String>();
		
		for(Category cat : categories) {
			categoriesArray.add(cat.getName());
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categoriesArray);
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
	    	insertTaskAction(getActivity());
	      break;
	    }

	    return true;
	  } 



	
	@SuppressLint("SimpleDateFormat")
	private boolean insertTaskAction(Context context) {
		
		if(!Validator.hasText(getActivity(), titleTxt) || !Validator.isNumber(getActivity(), coast, true))
		{
			return false;
		}

		Calendar selected = new GregorianCalendar(dueDatePicker.getYear(), dueDatePicker.getMonth(), dueDatePicker.getDayOfMonth());
		SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy.MM.dd");
		String dueDateString = dateFormat.format(selected.getTime());
		
		taskDataSource.open();
		
		taskDataSource.createTask(titleTxt.getText().toString(), categoriesSpinner.getSelectedItemId(), Long.parseLong(coast.getText().toString()), dueDateString, note.getText().toString(), reminderTxtView.getText().toString());
		taskDataSource.close();
		Toast.makeText(context, "Task Created", Toast.LENGTH_SHORT).show();
		return true;
	}

}
