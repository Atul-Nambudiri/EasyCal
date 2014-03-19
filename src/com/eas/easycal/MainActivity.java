package com.eas.easycal;

import java.util.Locale;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {
	
	/**
	 * Test stuff
	 **/
	private IndividualEvent testEvents[];
	private String[][] testEventsS = new String[12][];
	int currCal = 0;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	private String[] mTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    
    private newListFragment nlf;
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		mTitles = getResources().getStringArray(R.array.testList);
		mTitle = mDrawerTitle = getTitle();
	    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	    mDrawerList = (ListView) findViewById(R.id.left_drawer);
	        // Set the adapter for the list view
	    mDrawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mTitles));
	        // Set the list's click listener
	    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle); 
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
            }
        };
        
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
        
        
        
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the two
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void initTest(int pos) {
		switch (pos) {
		case 0:
			testEvents = new IndividualEvent[] {
					new IndividualEvent(1, "MATH 231", "12", "1:00", "Natural History Building"), 
					new IndividualEvent(1, "EALC 250", "13", "1:00", "Main Library"),
					new IndividualEvent(1, "CS 125", "14", "1:00", "Digital Computer Laboratory"),
					new IndividualEvent(1, "CS 173", "15", "1:00", "Digital Computer Laboratory"),
					new IndividualEvent(1, "ENG 100", "16", "1:00", "Everitt Laboratory"),
					new IndividualEvent(1, "CS 100", "17", "1:00", "Natural History Building"),
					new IndividualEvent(1, "CS 398", "18", "1:00", "Everitt Laboratory"),
					new IndividualEvent(1, "PHYS 214", "19", "1:00", "Loomis Lab"),
					new IndividualEvent(1, "Lunch", "20", "1:00", "Illinois Street Residence Hall"),
					new IndividualEvent(1, "CS 225", "21", "1:00", "Siebel Center for Computer Science"),
					new IndividualEvent(1, "MATH 241", "22", "1:00", "Altgeld Hall"),
					new IndividualEvent(1, "SigMobile", "23", "1:00", "Siebel Center for Computer Science")
			};
			testEventsS = new String[testEvents.length][];
			for(int i = 0; i < testEventsS.length; i++){
				testEventsS[i] = testEvents[i].getArray();
			}
			currCal = 0;
			break;
		case 1:
			testEvents = new IndividualEvent[] {
					new IndividualEvent(1, "MATH 231", "12", "1:00", "Natural History Building"), 
					new IndividualEvent(1, "EALC 250", "13", "1:00", "Main Library"),
					new IndividualEvent(1, "CS 125", "14", "1:00", "Digital Computer Laboratory"),
					new IndividualEvent(1, "CS 173", "15", "1:00", "Digital Computer Laboratory")
			};
			testEventsS = new String[testEvents.length][];
			for(int i = 0; i < testEvents.length; i++){
				testEventsS[i] = testEvents[i].getArray();
			}
			currCal = 1;
			break;
		case 2:
			testEvents = new IndividualEvent[] {
					new IndividualEvent(1, "ENG 100", "16", "1:00", "Everitt Laboratory"),
					new IndividualEvent(1, "CS 100", "17", "1:00", "Natural History Building"),
					new IndividualEvent(1, "CS 398", "18", "1:00", "Everitt Laboratory"),
					new IndividualEvent(1, "PHYS 214", "19", "1:00", "Loomis Lab")
			};
			testEventsS = new String[testEvents.length][];
			for(int i = 0; i < testEvents.length; i++){
				testEventsS[i] = testEvents[i].getArray();
			}
			currCal = 2;
			break;
		case 3:
			testEvents = new IndividualEvent[] {
					new IndividualEvent(1, "Lunch", "20", "1:00", "Illinois Street Residence Hall"),
					new IndividualEvent(1, "CS 225", "21", "1:00", "Siebel Center for Computer Science"),
					new IndividualEvent(1, "MATH 241", "22", "1:00", "Altgeld Hall"),
					new IndividualEvent(1, "SigMobile", "23", "1:00", "Siebel Center for Computer Science")
			};
			testEventsS = new String[testEvents.length][];
			for(int i = 0; i < testEvents.length; i++){
				testEventsS[i] = testEvents[i].getArray();
			}
			currCal = 3;
			break;
		}
	}
	
	public void addEventDialog() {
		 AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 LayoutInflater inflater = this.getLayoutInflater();
		 final View v = inflater.inflate(R.layout.event_dialog_layout, null);
		 builder.setView(v); 
		 builder.setPositiveButton("Add Event", new DialogInterface.OnClickListener() {
		      @Override
		      public void onClick(DialogInterface dialog, int id) {
		    	  String name = ((EditText) v.findViewById(R.id.name)).getText().toString();
		          String day = ((EditText) v.findViewById(R.id.day)).getText().toString();
		          String time = ((EditText) v.findViewById(R.id.time)).getText().toString();
		          String location = ((EditText) v.findViewById(R.id.location)).getText().toString();
		          IndividualEvent t = new IndividualEvent(1, name, day, time, location);
		          String[][] testEventsS1 = new String[testEventsS.length + 1][];
		          for(int i = 0; i < testEventsS.length; i ++) {
		        	  testEventsS1[i] = testEventsS[i];
		          }
		          testEventsS1[testEventsS.length] = t.getArray();
		          testEventsS = testEventsS1;
		          nlf.update(testEventsS);               
		                    
		        dialog.dismiss();
		      }

		    });
		 builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int id) {
			        dialog.cancel();
			    }
		 });
		 AlertDialog dialog = builder.create();
		 dialog.show();
		 
	}
	
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        
        switch (item.getItemId()) {
			case R.id.action_add:
				addEventDialog();
				return true;
       }
        return super.onOptionsItemSelected(item);
	}
	
	   
	

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
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
	 
	 
	 
	 

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				initTest(0);
				nlf = new newListFragment();
				return nlf;
			
			case 1:
				Fragment fragment = new CalendarFragment();
				return fragment;
			}
			return null;
			
		}

		@Override
		public int getCount() {
			// Show 2 total pages.
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section2).toUpperCase(l);
			case 1:
				return getString(R.string.title_section1).toUpperCase(l);			
			}
			return null;
		}
	}
	/*
	 * Listens for user item clicks in drawer
	 */
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if(position != currCal) {
				initTest(position);
				nlf.update(testEventsS);
			}
			mDrawerList.setItemChecked(position, true);
			mDrawerLayout.closeDrawer(mDrawerList);
	    }
	}
	
	

	
	

}
