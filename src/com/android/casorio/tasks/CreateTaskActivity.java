package com.android.casorio.tasks;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.casorio.R;
import com.android.casorio.util.ActivityStarter;

public class CreateTaskActivity extends Activity {
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_create_layout);
		
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
	    
	    case R.id.action_insert_guest:
	    	insertTaskAction(this);
	      break;
	    }

	    return true;
	  } 



	
	private boolean insertTaskAction(Context context) {
		
		return false;
	}

}
