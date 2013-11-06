package com.android.casorio.tasks;

import java.text.NumberFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

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
			
			holder.name = (TextView) convertView.findViewById(R.id.task_task_name_txtView);
			holder.coast = (TextView) convertView.findViewById(R.id.task_task_coast_txtView);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}


		final Task task = mTaskList.get(position);

			holder.box.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				task.setCompleted(isChecked);
				source.open();
				source.updateGuest(task);
				source.close();
			}
		});
			
		String coastFormated = NumberFormat.getCurrencyInstance().format(task.getCoast());


		holder.name.setText(task.getName());
		holder.coast.setText(coastFormated);

		holder.box.setChecked(task.isCompleted());
		return convertView;
	}

	static class ViewHolder {

		CheckBox box;
		TextView name;
		TextView coast;
	}

}
