package com.android.casorio.guest;

import com.lucasfe.casorio.R;
import com.android.casorio.database.datasources.GuestDataSource;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class GuestDeletedDialogFragment extends DialogFragment {

	
	public static final String GUEST_ID = "guestId";
	
	private long guestId = 0;

	@Override
	public void onStart() {
		super.onStart();

		Bundle args = getArguments();
		if (args != null) {
			guestId = args.getLong(GUEST_ID);
		}
	}

	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	
    	String title = getString(R.string.guest_details_delete_dialog_title);
    	String message = getString(R.string.guest_details_delete_dialog_message);
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
                	if (guestId != 0) {
                		GuestDataSource mSource = new GuestDataSource(getActivity());
                        mSource.open();
                        mSource.deleteGuest(guestId);
                        mSource.close();	
                	}
                	
                }
            })
            .create();
    }
}
