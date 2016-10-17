package com.example.vineet.myapplicationone;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.vineet.myapplicationone.Tab_Fragments.AllOrders_Fragment;
import com.example.vineet.myapplicationone.Tab_Fragments.LastWeek_Fragment;
import com.example.vineet.myapplicationone.Tab_Fragments.Older_Fragment;

/**
 * Created by Vineet on 10/1/2016.
 */
public class TabFragment extends android.support.v4.app.Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int items = 3;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container , Bundle savedInstanceState) {
        View bob_view = inflater.inflate(R.layout.bob_tab_layout,null);
        tabLayout = (TabLayout) bob_view.findViewById(R.id.bob_tab_id);
        viewPager = (ViewPager)bob_view.findViewById(R.id.bob_view_pager_id);

        viewPager.setAdapter(new Adap(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return bob_view;

    }

    class Adap extends FragmentPagerAdapter {

        public Adap(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position){
                case 0 : return new AllOrders_Fragment();
                case 1 : return new LastWeek_Fragment();
                case 2 : return new Older_Fragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0 :
                    return "All";
                case 1:
                    return "This Week";
                case 2:
                    return "Last Week";
            }
            return null;
        }
    }
}
