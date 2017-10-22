package com.xemss.smartcommunityapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String[] mScreenTitle;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: 19.10.2017 get view
        mTitle = mDrawerTitle = getTitle();
        mScreenTitle = getResources().getStringArray(R.array.list);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//
//        // TODO: 19.10.2017 set adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.drawer_list_item,
                mScreenTitle
        );

        mDrawerList.setAdapter(adapter);

        // TODO: 19.10.2017 set click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // TODO: 20.10.2017 enable home and back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                // TODO: 22.10.2017 problems with drawer icon
//                R.drawable.ic_drawer,
//                android.support.v7.widget.Toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu();
//                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                getActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu();
//                super.onDrawerClosed(drawerView);
            }
        };

        // TODO: 22.10.2017 set drawer listener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // TODO: 22.10.2017 default selected item
        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO: 22.10.2017 populate menu with options
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // TODO: 22.10.2017 hide option search when drawer open
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_search).setVisible(!drawerOpen);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, R.string.action_search, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }


    }

    private void selectItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new TopFragment();
                break;
            case 1:
                fragment = new MyBalanceFragment();
                break;
            case 2:
                fragment = new MyTasksFragment();
                break;
            case 3:
                fragment = new MyEventsFragment();
                break;
            case 4:
                fragment = new MyMemosFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            // TODO: 22.10.2017 set fragment
//            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();

            // TODO: 22.10.2017 set item in list checked
            mDrawerList.setItemChecked(position, true);
            // TODO: 22.10.2017 set title
            setTitle(mScreenTitle[position]);
            // TODO: 22.10.2017 close drawer
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            Log.d(this.getClass().getName(), "Error, fragment doesn't created");
        }

//        @Override
////        public CharSequence title = null;
//        setTitle(CharSequence title) {
//            mTitle = title;
//        }
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        // TODO: 22.10.2017 synchronize after restore
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // TODO: 22.10.2017 when configuration change
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}