package com.android.casorio.tasks;

public interface ITaskListener {
	
	
	public void OnTaskSelected(long id);
	public void OnTaskDeleted(long id);
	public void OnTaskUpdated(long id);
	public void OnCreateTask();

}
