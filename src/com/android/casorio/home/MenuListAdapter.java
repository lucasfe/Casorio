package com.android.casorio.home;

import java.util.Arrays;
import java.util.List;

import com.android.casorio.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuListAdapter extends BaseAdapter{

	
	private Context mContext;
	LayoutInflater mInflater;
	
	TextView menuNameTitleTxtView;
	ImageView menuImageView;
	
	List<String> menuEntries;
	
	public MenuListAdapter(Context context) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		menuEntries = Arrays.asList( mContext.getResources().getStringArray(R.array.home_menu_entries));
	}
	
	@Override
	public int getCount() {
		
		return menuEntries.size();
	}

	@Override
	public Object getItem(int position) {

		return menuEntries.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = mInflater
					.inflate(R.layout.home_drawer_menu_item, null);
		}
		
		menuImageView = (ImageView) convertView.findViewById(R.id.home_drawer_menu_image);
		menuNameTitleTxtView = (TextView) convertView.findViewById(R.id.home_drawer_menu_text);

		setMenuImage(position);
		
		menuNameTitleTxtView.setText(menuEntries.get(position));
		
		return convertView;
	}
	
	
	private void setMenuImage(int position) {
		
		switch (position) {
		case 0:
			menuImageView.setImageResource(R.drawable.home_menu_home_icon);
			break;
		case 1:
			menuImageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.home_menu_guests_icon));
			break;	
		case 2:
			menuImageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.home_menu_tasks_icon));
			break;
		case 4:
			menuImageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.home_menu_settings_icon));
			break;	

		default:
			menuImageView.setImageDrawable(mContext.getResources().getDrawable(
					R.drawable.social_group));
			break;
		}
	}

}
