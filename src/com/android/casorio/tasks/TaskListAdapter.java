package com.android.casorio.tasks;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.android.casorio.R;
import com.android.casorio.database.datasources.TaskDataSource;

public class TaskListAdapter extends BaseAdapter {

	LayoutInflater mInflater;
	CheckBox taskCheckBox;
	TaskDataSource source;

	List<Task> mTaskList;

	public void setTaskList(List<Task> mTaskList) {
		this.mTaskList = mTaskList;
	}

	public TaskListAdapter(List<Task> taskList, Context context) {
		super();
		source = new TaskDataSource(context);
		mInflater = LayoutInflater.from(context);
		mTaskList = taskList;
	}

	@Override
	public int getCount() {
		return mTaskList.size();
	}

	@Override
	public Object getItem(int position) {
		return mTaskList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return mTaskList.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.task_list_item, null);
			holder = new ViewHolder();
			holder.box = (CheckBox) convertView
					.findViewById(R.id.taskListCheckbox);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Task task = mTaskList.get(position);

		holder.box.setText(task.getName());

		return convertView;
	}

	static class ViewHolder {

		CheckBox box;
	}

}
