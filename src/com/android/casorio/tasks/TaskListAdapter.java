package com.android.casorio.tasks;

import com.android.casorio.R;
import com.android.casorio.database.datasources.TaskDataSource;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class TaskListAdapter extends CursorAdapter{

	LayoutInflater mInflater;
	
	CheckBox taskCheckBox;
	
	@SuppressWarnings("deprecation")
	public TaskListAdapter(Context context, Cursor c) {
		super(context, c);
		mInflater = LayoutInflater.from(context);

	
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		
		taskCheckBox = (CheckBox) view.findViewById(R.id.taskListCheckbox);
		
		Task task = TaskDataSource.cursorToTask(cursor);
		taskCheckBox.setText(task.getName());
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		final View view = mInflater.inflate(R.layout.task_list_item, parent, false);
		return view;
	}

}
