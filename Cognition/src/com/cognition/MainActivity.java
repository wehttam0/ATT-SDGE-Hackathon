package com.cognition;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private String[] drawerTitles;
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	private CustomActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
        drawerTitles = getResources().getStringArray(R.array.drawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        
     // Check that the activity is using the layout version with
     		// the fragment_container FrameLayout
     		if (findViewById(R.id.fragment_container) != null) {

     			// However, if we're being restored from a previous state,
     			// then we don't need to do anything and should return or else
     			// we could end up with overlapping fragments.
     			if (savedInstanceState != null) {
     				return;
     			}

     			// Create a the default Fragment to be placed in the activity layout
     			DashboardFrag dbFragment = new DashboardFrag();
     			
     			// Add the fragment to the 'fragment_container' FrameLayout
     			getFragmentManager().beginTransaction().add(R.id.fragment_container, dbFragment).commit();
     		}

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, drawerTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        // Set the list's click listener
        mDrawerToggle = new CustomActionBarDrawerToggle(this, mDrawerLayout);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
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
    
	private class CustomActionBarDrawerToggle extends ActionBarDrawerToggle {

        public CustomActionBarDrawerToggle(Activity mActivity,DrawerLayout mDrawerLayout){
                super(
                    mActivity,
                    mDrawerLayout, 
                    R.drawable.ic_drawer,
                    R.string.drawer_open, 
                    R.string.drawer_close);
        }

        @Override
        public void onDrawerClosed(View view) {
                getActionBar().setTitle(getString(R.string.cog_en));
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
        }

        @Override
        public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(getString(R.string.cog_en));
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
        }
}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Highlight the selected item, update the title, and close the drawer
                // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        Log.e("Position!!", String.valueOf(position));
        if(position == 1){
        profile_frag pFragment = new profile_frag();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, pFragment).commit();
        }else{
        	// Create a the default Fragment to be placed in the activity layout
 			DashboardFrag dbFragment = new DashboardFrag();
 			getFragmentManager().beginTransaction().replace(R.id.fragment_container, dbFragment).commit();
        }
        
        //You should reset item counter 
        mDrawerLayout.closeDrawer(mDrawerList);
                
        }
}
    
    
}
