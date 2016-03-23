package com.developer.codingchallenge.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;

import com.developer.codingchallenge.R;
import com.developer.codingchallenge.bus.RxBus;
import com.developer.codingchallenge.ui.fragment.ContactListFragment;
import com.developer.codingchallenge.ui.fragment.UserDataFragment;
import com.developer.codingchallenge.ui.fragment.UserOptionsFragment;
import com.developer.codingchallenge.ui.fragment.WeatherFragment;

public class MainActivity extends AppCompatActivity {

    private static final int USER_INFORMATION = 0;
    private static final int CONTACT_LIST = 1;
    private static final int WEATHER = 2;
    private static final int OPTIONS = 3;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private RxBus rxBus = null;
    private Toolbar mToolbar;

    public RxBus getRxBusSingleton() {
        if (rxBus == null) {
            rxBus = new RxBus();
        }
        return rxBus;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setToolbarTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setToolbarTitle(0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setToolbarTitle(int toolbarTitle) {
        getSupportActionBar().setTitle(mSectionsPagerAdapter.getPageTitle(toolbarTitle));
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case USER_INFORMATION:
                    fragment = UserDataFragment.newInstance();
                    break;
                case CONTACT_LIST:
                    fragment = ContactListFragment.newInstance();
                    break;
                case WEATHER:
                    fragment = WeatherFragment.newInstance();
                    break;
                case OPTIONS:
                    fragment = UserOptionsFragment.newInstance();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.user_information);
                case 1:
                    return getString(R.string.user_contact);
                case 2:
                    return getString(R.string.user_weather);
                case 3:
                    return getString(R.string.user_form);
            }
            return null;
        }

    }

}
