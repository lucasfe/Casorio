package com.android.casorio.tasks;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.casorio.R;
import com.android.casorio.database.TaskDataSource;
import com.android.casorio.util.ActivityStarter;

public class CreateTaskActivity extends Activity {
	
	
	TaskDataSource taskDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_create_layout);
		taskDataSource = new TaskDataSource(this); 
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
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
			ActivityStarter.callHomeActivity(this);
			break;
	    
	    case R.id.action_call_create_task:
	    	insertTaskAction(this);
	      break;
	    }

	    return true;
	  } 



	
	private boolean insertTaskAction(Context context) {
		taskDataSource.open();
		
		taskDataSource.createTask("Luketa", 0, 10, "seila", "seila", "seila");
		taskDataSource.close();
		Toast.makeText(context, "Task Created", Toast.LENGTH_SHORT).show();
		return true;
	}

}
