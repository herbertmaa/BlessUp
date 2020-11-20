package com.bcit.comp3717project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import fragment.ListChurches;
import fragment.MapChurches;

public class ViewChurchesActivity extends AppCompatActivity {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();


    public class SectionsPageAdapter extends FragmentPagerAdapter {

        private int count = 0;
        public SectionsPageAdapter(FragmentManager fm) { super(fm); }

        @Override
        public int getCount() { return count; }


        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
            count++;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_churches);

        TabLayout tabLayout = findViewById(R.id.tableLayout);
        ViewPager pager = findViewById(R.id.pager);
        tabLayout.setupWithViewPager(pager);

        SectionsPageAdapter pagerAdapter = new SectionsPageAdapter(getSupportFragmentManager()){};
        pagerAdapter.addFragment(new ListChurches(), "List");
        pagerAdapter.addFragment(new MapChurches(), "Map");
        pager.setAdapter(pagerAdapter);

    }
}