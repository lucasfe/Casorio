package com.android.casorio.guest;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.casorio.R;
import com.android.casorio.guest.Guest.GuestStatus;

public class GuestAdapter extends ArrayAdapter<Guest> {

	Context context;
	int layoutResourceId;
	Guest[] data = null;
	Drawable socialGroupImg;
	
	
	public GuestAdapter(Context context, int layoutResourceId, Guest[] data) {
		super(context, layoutResourceId, data);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.data = data;
		socialGroupImg = context.getResources().getDrawable(R.drawable.social_group);
		
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) { 
		
		View row = convertView;
		GuestHolder holder = null;
		
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new GuestHolder();
		holder.txtName = (TextView) row.findViewById(R.id.guest_view_name);
		holder.txtStatus = (TextView) row.findViewById(R.id.guest_view_status);
		holder.imgNumberOfGuests = (ImageView) row.findViewById(R.id.guest_view_number_of_guests_img);

		Guest guest = data[position];
		mountGuestRow(holder, guest);
				
		return row;
	}
	
	static class GuestHolder {
		TextView txtName;
		TextView txtStatus;
		ImageView imgNumberOfGuests;
	}
	
	
	private void mountGuestRow(GuestHolder holder, Guest guest) {
		
		String status = context.getResources().getString(R.string.guest_pending_status);
		GuestStatus value = guest.getGuestStatus();
		
		
		switch (value) {
		case INVITE_SENT:
			status = context.getResources().getString(R.string.guest_sent_status);
			break;
		case PENDING:
			status = context.getResources().getString(R.string.guest_pending_status);
			break;	
		case CONFIRMED:
			status = context.getResources().getString(R.string.guest_confirmed_status);
			break;
		default:
		
		}
		
		holder.txtName.setText(guest.getName());
		holder.txtStatus.setText(status);
		
		if(guest.getAdditinal_guests() > 1) {
			holder.imgNumberOfGuests.setImageDrawable(socialGroupImg);
		}
		
	}
	
}
