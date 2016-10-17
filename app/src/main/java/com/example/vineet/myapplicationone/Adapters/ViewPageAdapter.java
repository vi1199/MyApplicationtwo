package com.example.vineet.myapplicationone.Adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.vineet.myapplicationone.TabFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vineet on 10/1/2016.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {

    private final List<TabFragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFrag(TabFragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

}
