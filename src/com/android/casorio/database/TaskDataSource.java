package com.android.casorio.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.android.casorio.database.tables.TasksTable;
import com.android.casorio.guest.Guest.GuestStatus;
import com.android.casorio.guest.Guest.GuestType;

public class TaskDataSource extends GenericDataSource {
	
	
	public TaskDataSource(Context context)
	{
		dbHelper = new CasorioDatabase(context);
	}
	
	
	private String[] allColumns = { TasksTable.COLUMN_ID, TasksTable.COLUMN_NAME, 
			TasksTable.COLUMN_CATEGORY_ID,  TasksTable.COLUMN_COAST, TasksTable.COLUMN_DUE_DATE, TasksTable.COLUMN_NOTE, TasksTable.COLUMN_REMINDER };

	
	
	public Task createTask(String name, String email, int additionalGuest, GuestType type, GuestStatus status) {
		ContentValues values = new ContentValues();
		
		values.put(TasksTable.COLUMN_NAME, name);
		values.put(TasksTable.COLUMN_CATEGORY_ID, email);
		values.put(TasksTable.COLUMN_COAST, additionalGuest);
		values.put(TasksTable.COLUMN_DUE_DATE, type.getValue());
		values.put(TasksTable.COLUMN_NOTE, status.getValue());
		values.put(TasksTable.COLUMN_REMINDER, status.getValue());
		
		
		long insertId = database.insert(TasksTable.TABLE_TASKS, null,
				values);
		Cursor cursor = database.query(TasksTable.TABLE_TASKS,
				allColumns, TasksTable.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Task newTask = cursorToTask(cursor);
		cursor.close();
		return newTask;
	}
	
	
	public Task cursorToTask(Cursor cursor) {
		
		Task newTask = new Task();
		
		newTask.setId(cursor.getLong(0));
		newTask.setName(cursor.getString(1));
		newTask.setCategory_id(cursor.getString(2));
		newTask.setCoast(cursor.getInt(3));
		newTask.setDueDate(cursor.getString(4));
		newTask.setNote(cursor.getString(5));
		newTask.setRemimder(cursor.getString(6));

		return newTask;
	}
	
	public List<Task> getAllTasks() {
		List<Task> result = new ArrayList<Task>();
		
		Cursor cursor = getAllTasksCursor();
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			
			Task tempTask = cursorToTask(cursor);
			result.add(tempTask);
			
		}
		cursor.close();
		
		return result;
	}
	
	public Cursor getAllTasksCursor() {
		
		return database.query(TasksTable.TABLE_TASKS, allColumns, null, null, null, null, null);
		
	}
	


}
