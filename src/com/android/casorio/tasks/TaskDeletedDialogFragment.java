package com.android.casorio.tasks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

import com.android.casorio.R;
import com.android.casorio.database.datasources.TaskDataSource;

public class TaskDeletedDialogFragment extends DialogFragment {

	
	public static final String TASK_ID = "taskId";
	
	private long taskId = 0;

	@Override
	public void onStart() {
		super.onStart();

		Bundle args = getArguments();
		if (args != null) {
			taskId = args.getLong(TASK_ID);
		}
	}

	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	
    	String title = getString(R.string.task_details_delete_dialog_title);
    	String message = getString(R.string.task_details_delete_dialog_message);
        return new AlertDialog.Builder(getActivity())
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(android.R.string.no, new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing (will close dialog)
                }
            })
            .setPositiveButton(android.R.string.yes,  new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                	if (taskId != 0) {
                		TaskDataSource mSource = new TaskDataSource(getActivity()) ;
                        mSource.open();
                        mSource.deleteTask(taskId);
                        mSource.close();	
                	}
                	
                }
            })
            .create();
    }
}
