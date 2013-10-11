package com.android.casorio.tasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.android.casorio.util.FragmentCaller;
import com.android.casorio.util.Validator;

public class CreateTaskActivity extends Activity {
	
	
	TaskDataSource taskDataSource;
	CategoriesDataSource categoriesDataSource;
	
	
	EditText titleTxt;
	DatePicker dueDatePicker;
	EditText coast;
	EditText note;
	TextView reminderTxtView;
	Spinner categoriesSpinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_create_layout);
		taskDataSource = new TaskDataSource(this); 
		categoriesDataSource = new CategoriesDataSource(this);
		
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		titleTxt = (EditText) findViewById(R.id.taskNameText);
		dueDatePicker = (DatePicker) findViewById(R.id.task_due_date_picker);
		coast = (EditText) findViewById(R.id.task_coast_text);
		note = (EditText) findViewById(R.id.task_note_text);
		reminderTxtView = (TextView) findViewById(R.id.task_reminder_txt_view);
		categoriesSpinner = (Spinner) findViewById(R.id.task_category_spinner);
		
		categoriesDataSource.open();
		List<Category> categories = categoriesDataSource.getAllCategories();
		categoriesDataSource.close();
		List<String> categoriesArray = new ArrayList<String>();
		
		for(Category cat : categories) {
			categoriesArray.add(cat.getName());
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categoriesArray);
		categoriesSpinner.setAdapter(adapter);
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.task_create_menu, menu);
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
		
	    case android.R.id.home:
			FragmentCaller.callHomeActivity(this);
			break;
	    
	    case R.id.action_call_create_task:
	    	insertTaskAction(this);
	      break;
	    }

	    return true;
	  } 



	
	@SuppressLint("SimpleDateFormat")
	private boolean insertTaskAction(Context context) {
		
		if(!Validator.hasText(this, titleTxt) || !Validator.isNumber(this, coast, true))
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
