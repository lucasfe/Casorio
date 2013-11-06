package com.android.casorio.tasks;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;

import com.android.casorio.R;
import com.android.casorio.database.tables.TasksTable;

public class Task implements Serializable {

	/**
	 * Serial UID for Serializable interface
	 */
	private static final long serialVersionUID = 1L;

	private Context mContext;

	private long id;

	private String name;
	private long category_id;
	private long coast;
	private long dueDate;
	private String note;
	private boolean isCompleted;
	private int taskPrefix;

	public Task(Context context) {
		mContext = context;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		
		if (!TextUtils.isEmpty(name)
				&& name.contains(TasksTable.TASKS_NAME_PREFIX)) {
			return getPredefinedTaskName();
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCategoryId() {
		return category_id;
	}

	public void setCategoryId(long category_id) {
		this.category_id = category_id;
	}

	public long getCoast() {
		return coast;
	}

	public void setCoast(int coast) {
		this.coast = coast;
	}

	public long getDueDate() {
		return dueDate;
	}

	public void setDueDate(long dueDate) {
		this.dueDate = dueDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getCompletedValue() {
		return isCompleted ? 1 : 0;
	}

	private String getPredefinedTaskName() {

		List<String> categories = Arrays.asList(mContext.getResources()
				.getStringArray(R.array.predefined_tasks));

		return categories.get(getTaskPrefix());

	}

	public int getTaskPrefix() {
		return taskPrefix;
	}

	public void setTaskPrefix(int value) {
		taskPrefix = value;
	}

}
