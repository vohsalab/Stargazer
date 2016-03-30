package com.firstsputnik.stargazer.View;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firstsputnik.stargazer.R;


import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private int fragmentId;


    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }*/
        mNavView.setNavigationItemSelectedListener(new DrawerItemClickListener());
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.drawer_open, R.string.drawer_close) {


            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);


            }


            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        if(savedInstanceState==null)
        {
            Menu menu = mNavView.getMenu();
            fragmentId = R.id.meet_iss_item;
            selectItem(menu.findItem(fragmentId));
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
//        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
       // mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            selectItem(item);
            return true;
        }
    }
    private void selectItem(MenuItem position) {
        //Toast.makeText(this, "Position:" + position, Toast.LENGTH_SHORT).show();
        Fragment fragment;
        switch (position.getItemId()) {
            case R.id.meet_iss_item:
                fragment = new MeetISSFragment();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.iss_now_item:
                fragment = new ISSNowMapFragment();
                mDrawerLayout.closeDrawers();
                break;
            case R.id.apod_item:
                fragment = new APODFragment();
                mDrawerLayout.closeDrawers();
                break;
            default:
                fragment = new MeetISSFragment();
                break;
        }
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.content_main_frame, fragment)
                .commit();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("FragmentID", fragmentId);
    }
}


