package com.android.casorio.guest;

import com.android.casorio.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GuestAdapter extends ArrayAdapter<Guest> {

	Context context;
	int layoutResourceId;
	Guest[] data = null;
	
	public GuestAdapter(Context context, int layoutResourceId, Guest[] data) {
		super(context, layoutResourceId, data);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.data = data;
		
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) { 
		
		View row = convertView;
		GuestHolder holder = null;
		
		if (row == null) { 
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new GuestHolder();
			holder.txtName = (TextView)row.findViewById(R.id.guest_view_name);
			holder.txtStatus = (TextView)row.findViewById(R.id.guest_view_status);
			holder.txtAdditional = (TextView)row.findViewById(R.id.guest_view_addtional_number);
		}
		else { 
			holder = (GuestHolder) row.getTag();
		}
		
		Guest guest = data[position];
		holder.txtName.setText(guest.getName());
		holder.txtStatus.setText(String.valueOf(guest.getStatus()));
		holder.txtAdditional.setText(String.valueOf(guest.getAdditinal_guests()));
		
		
		return row;
	}
	
	static class GuestHolder {
		TextView txtName;
		TextView txtStatus;
		TextView txtAdditional;
	}

}
