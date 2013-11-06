package com.android.casorio.guest;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lucasfe.casorio.R;
import com.android.casorio.database.datasources.GuestDataSource;
import com.android.casorio.guest.Guest.GuestStatus;

public class GuestAdapter extends CursorAdapter {
	private Context context;
	private final LayoutInflater mInflater;

	int layoutResourceId;
	Guest[] data = null;
	Drawable socialGroupImg;

	@SuppressWarnings("deprecation")
	public GuestAdapter(Context context, Cursor c) {
		super(context, c);
		this.context = context;
		mInflater = LayoutInflater.from(context);
		socialGroupImg = context.getResources().getDrawable(
				R.drawable.social_group);

	}

	static class GuestHolder {
		TextView txtName;
		TextView txtStatus;
		ImageView imgNumberOfGuests;
	}

	private void mountGuestRow(GuestHolder holder, Guest guest) {

		String status = context.getResources().getString(
				R.string.guest_pending_status);
		GuestStatus value = guest.getGuestStatus();

		switch (value) {
		case NOT_GOING:
			status = context.getResources().getString(
					R.string.guest_not_going);
			break;
		case PENDING:
			status = context.getResources().getString(
					R.string.guest_pending_status);
			break;
		case CONFIRMED:
			status = context.getResources().getString(
					R.string.guest_confirmed_status);
			break;
		default:

		}

		holder.txtName.setText(guest.getName());
		holder.txtStatus.setText(status);

		if (guest.getAdditinal_guests() > 1) {
			holder.imgNumberOfGuests.setImageDrawable(socialGroupImg);
		}

	}

	@Override
	public void bindView(View row, Context arg1, Cursor cursor) {

		GuestHolder holder = new GuestHolder();
		holder.txtName = (TextView) row.findViewById(R.id.guest_view_name);
		holder.txtStatus = (TextView) row.findViewById(R.id.guest_view_status);
		holder.imgNumberOfGuests = (ImageView) row
				.findViewById(R.id.guest_view_number_of_guests_img);

		Guest guest = GuestDataSource.cursorToGuest(cursor);
		mountGuestRow(holder, guest);

	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		final View view = mInflater.inflate(R.layout.guest_list_item, parent,
				false);
		return view;
	}

}
