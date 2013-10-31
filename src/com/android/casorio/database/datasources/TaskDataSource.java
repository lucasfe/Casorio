package com.android.casorio.database.datasources;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.android.casorio.database.CasorioDatabase;
import com.android.casorio.database.GenericDataSource;
import com.android.casorio.database.tables.TasksTable;
import com.android.casorio.tasks.Task;

public class TaskDataSource extends GenericDataSource {

	public TaskDataSource(Context context) {
		dbHelper = new CasorioDatabase(context);
	}

	private String[] allColumns = { TasksTable.COLUMN_ID,
			TasksTable.COLUMN_NAME, TasksTable.COLUMN_CATEGORY_ID,
			TasksTable.COLUMN_COAST, TasksTable.COLUMN_DUE_DATE,
			TasksTable.COLUMN_NOTE, TasksTable.COLUMN_STATUS ,TasksTable.COLUMN_REMINDER,
			};

	public Task createTask(String name, long categoryId, long coast,
			long dueDate, String note, int status) {
		ContentValues values = new ContentValues();

		values.put(TasksTable.COLUMN_NAME, name);
		values.put(TasksTable.COLUMN_CATEGORY_ID, categoryId);
		values.put(TasksTable.COLUMN_COAST, coast);
		values.put(TasksTable.COLUMN_DUE_DATE, dueDate);
		values.put(TasksTable.COLUMN_NOTE, note);
		values.put(TasksTable.COLUMN_STATUS, status);
		values.put(TasksTable.COLUMN_REMINDER, "");

		long insertId = database.insert(TasksTable.TABLE_TASKS, null, values);
		Cursor cursor = database
				.query(TasksTable.TABLE_TASKS, allColumns, TasksTable.COLUMN_ID
						+ " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Task newTask = cursorToTask(cursor);
		cursor.close();
		return newTask;
	}

	public static Task cursorToTask(Cursor cursor) {

		Task newTask = new Task();

		newTask.setId(cursor.getLong(0));
		newTask.setName(cursor.getString(1));
		newTask.setCategoryId(cursor.getLong(2));
		newTask.setCoast(cursor.getInt(3));
		newTask.setDueDate(cursor.getLong(4));
		newTask.setNote(cursor.getString(5));
		newTask.setCompleted((cursor.getInt(6) > 0 ));
		newTask.setRemimder(cursor.getString(7));


		return newTask;
	}

	public List<Task> getAllTasks() {
		List<Task> result = new ArrayList<Task>();

		Cursor cursor = getAllTasksCursor();

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {

			Task tempTask = cursorToTask(cursor);
			result.add(tempTask);
			cursor.moveToNext();

		}
		cursor.close();

		return result;
	}
	
	public List<Task> getCompletedTasks() {
		
		List<Task> result = new ArrayList<Task>();
		
		for(Task task : getAllTasks()) {
			if (task.isCompleted()) {
				result.add(task);
			}
		}
		return result;
	}

	public List<Task> getPendingTasks() {
		
		List<Task> result = new ArrayList<Task>();
		
		for(Task task : getAllTasks()) {
			if (!task.isCompleted()) {
				result.add(task);
			}
		}
		return result;
	}
	
	public Cursor getAllTasksCursor() {

		return database.query(TasksTable.TABLE_TASKS, allColumns, null, null,
				null, null, null);

	}

	public Cursor getTasksByCategoryCursor(long category) {
		return database.query(TasksTable.TABLE_TASKS, allColumns,
				TasksTable.COLUMN_CATEGORY_ID + "=?",
				new String[] { String.valueOf(category) }, null, null, null);
	}

	public List<Task> getTasksByCategory(long category) {

		Cursor filteredCategories = getTasksByCategoryCursor(category);
		List<Task> resultItems = new ArrayList<Task>();

		filteredCategories.moveToFirst();
		while (!filteredCategories.isAfterLast()) {
			resultItems.add(cursorToTask(filteredCategories));
			filteredCategories.moveToNext();
		}
		return resultItems;
	}
	
	public Task getTaskById(long id) {
		Cursor temp = database.query(TasksTable.TABLE_TASKS, allColumns,
				TasksTable.COLUMN_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null);
		temp.moveToFirst();
		Task returTask = null;
		if(temp.getCount() >= 1) {
			returTask = cursorToTask(temp);
		}
		
		return returTask ;
	}
	
	public void updateGuest(Task task) {
		
	    ContentValues values = new ContentValues();
	    
	    
		values.put(TasksTable.COLUMN_NAME, task.getName());
		values.put(TasksTable.COLUMN_CATEGORY_ID, task.getCategoryId());
		values.put(TasksTable.COLUMN_COAST, task.getCoast());
		values.put(TasksTable.COLUMN_DUE_DATE, task.getDueDate());
		values.put(TasksTable.COLUMN_NOTE, task.getNote());
		values.put(TasksTable.COLUMN_STATUS, task.getCompletedValue());
		values.put(TasksTable.COLUMN_REMINDER, task.getRemimder());

		database.update(TasksTable.TABLE_TASKS, values, TasksTable.COLUMN_ID + "=?" ,new String[]{String.valueOf(task.getId())});

	}

	
	
	public void deleteTask(long id) {
		database.delete(TasksTable.TABLE_TASKS, TasksTable.COLUMN_ID + "=?" ,new String[]{String.valueOf(id)});
	}

}
