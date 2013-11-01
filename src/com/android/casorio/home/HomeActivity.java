package com.android.casorio.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.casorio.R;
import com.android.casorio.categories.CategoryListFragment;
import com.android.casorio.categories.CreateCategoriesFragment;
import com.android.casorio.categories.IOnCategorySelectedListener;
import com.android.casorio.guest.GuestDeletedDialogFragment;
import com.android.casorio.guest.GuestDetailsFragment;
import com.android.casorio.guest.GuestFragment;
import com.android.casorio.guest.GuestInsertFragment;
import com.android.casorio.guest.IGuestListener;
import com.android.casorio.settings.SettingsFragment;
import com.android.casorio.tasks.CreateTaskFragment;
import com.android.casorio.tasks.ITaskListener;
import com.android.casorio.tasks.TaskDeletedDialogFragment;
import com.android.casorio.tasks.TaskDetailsFragment;
import com.android.casorio.tasks.TaskListFragment;
import com.android.casorio.util.FragmentCaller;





public class HomeActivity extends FragmentActivity implements IOnCategorySelectedListener, IGuestListener, ITaskListener {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_drawer_layout);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		
		
		mDrawerList.setAdapter(new MenuListAdapter(this));

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.action_add_guest,
				R.string.action_settings) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(getString(R.string.app_name));
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(getString(R.string.menu_name));
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		//Choose home fragment
		this.selectItem(0);

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...

		return super.onOptionsItemSelected(item);
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
		// Create a new fragment and specify the planet to show based on
		// position
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		Fragment destiny = null;
		
		switch (position) { 
		
		case 0:
			destiny = new HomeFragment();
			break;
		case 1:
			destiny = new GuestFragment();
			break;	
		case 2:
			destiny = new TaskListFragment();
			break;
		case 3:
			destiny = new CategoryListFragment();
			break;		
		case 4:
			destiny = new SettingsFragment();
			break;	
	
		default:
			destiny = new GuestFragment();
			break;
		}
		transaction.replace(R.id.content_frame, destiny).commit();
		

		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		getActionBar().setTitle("Luqueta");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.clear();
		getMenuInflater().inflate(R.menu.home_action_menu, menu);
		return true;
	}

	@Override
	public void onCreateCategory() {
		CreateCategoriesFragment createCategoryFrag = new CreateCategoriesFragment();
		FragmentCaller.callFragment(this, createCategoryFrag);

	}
	
	@Override
	public void onCategorySelected(long position) {
		
		TaskListFragment taskList = new TaskListFragment();
		Bundle args = new Bundle();
		args.putLong(TaskListFragment.CATEGORY_ID, position);
		taskList.setArguments(args);
		
		FragmentCaller.callFragment(this, taskList);
		
		
	}
	
	@Override
	public void onCategoryUpdated(long categoryId) {
		CreateCategoriesFragment createCategoryFrag = new CreateCategoriesFragment();
		Bundle args = new Bundle();
		args.putLong(CreateCategoriesFragment.CATEGORY_ID, categoryId);
		createCategoryFrag.setArguments(args);
		
		FragmentCaller.callFragment(this, createCategoryFrag);

	}

	@Override
	public void onGuestSelected(long position) {
		GuestDetailsFragment guestDetails = new GuestDetailsFragment();
		Bundle args = new Bundle();
		args.putLong(GuestDetailsFragment.GUEST_ID, position);
		guestDetails.setArguments(args);
		
		FragmentCaller.callFragment(this, guestDetails);
		
	}

	@Override
	public void onInsertGuest() {
		GuestInsertFragment guestInsert = new GuestInsertFragment();
		FragmentCaller.callFragment(this, guestInsert);

		
	}

	@Override
	public void onGuestDeleted(long id) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
		GuestDeletedDialogFragment guestDeleted = new GuestDeletedDialogFragment();
		
		Bundle args = new Bundle();
		args.putLong(GuestDeletedDialogFragment.GUEST_ID, id);
		guestDeleted.setArguments(args);

		guestDeleted.show(ft, "dialog");

	}

	@Override
	public void onGuestEdit(long id) {
		GuestInsertFragment guestInsert = new GuestInsertFragment();
		Bundle args = new Bundle();
		args.putLong(GuestInsertFragment.GUEST_ID, id);
		guestInsert.setArguments(args);

		FragmentCaller.callFragment(this, guestInsert);
		
	}

	@Override
	public void OnTaskSelected(long id) {
		TaskDetailsFragment taskDetails = new TaskDetailsFragment();
		Bundle args = new Bundle();
		args.putLong(TaskDetailsFragment.TASK_ID, id);
		taskDetails.setArguments(args);
		
		FragmentCaller.callFragment(this, taskDetails);
		
	}

	@Override
	public void OnTaskDeleted(long id) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
		TaskDeletedDialogFragment taskDeleted = new TaskDeletedDialogFragment();
		
		Bundle args = new Bundle();
		args.putLong(TaskDeletedDialogFragment.TASK_ID, id);
		taskDeleted.setArguments(args);

		taskDeleted.show(ft, "dialog");
		
	}

	@Override
	public void OnTaskUpdated(long id) {
		CreateTaskFragment taskInsert = new CreateTaskFragment();
		
		Bundle args = new Bundle();
		args.putLong(CreateTaskFragment.TASK_ID, id);
		taskInsert.setArguments(args);

		FragmentCaller.callFragment(this, taskInsert);
		
	}

	@Override
	public void OnCreateTask() {
		CreateTaskFragment taskInsert = new CreateTaskFragment();
		FragmentCaller.callFragment(this, taskInsert);		
	}



}
