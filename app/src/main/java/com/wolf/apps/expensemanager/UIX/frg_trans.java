package com.wolf.apps.expensemanager.UIX;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wolf.apps.expensemanager.MainActivity;
import com.wolf.apps.expensemanager.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class frg_trans extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Calendar current_date;
    private frg_trans_daily calling_frg_daily;
    private frg_trans_weekly calling_frg_weekly;
    private frg_trans_monthly calling_frg_monthly;
    private frg_trans_all calling_frg_all;

    public frg_trans() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frg_trans, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs_trans);
        viewPager = (ViewPager) view.findViewById(R.id.vp_tans);
        viewPager.setAdapter(new MainPagerAdapter(getFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        current_date = Calendar.getInstance();
    }

    class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    calling_frg_daily = new frg_trans_daily();
                    calling_frg_daily.setCurrent_date(current_date);
                    return calling_frg_daily;
                case 1:
                    calling_frg_weekly = new frg_trans_weekly();
                    calling_frg_weekly.setCurrent_date(current_date);
                    return calling_frg_weekly;
                case 2:
                    calling_frg_monthly = new frg_trans_monthly();
                    calling_frg_monthly.setCurrent_date(current_date);
                    return calling_frg_monthly;
                case 3:
                    calling_frg_all = new frg_trans_all();
                    calling_frg_all.setCurrent_date(current_date);
                    return calling_frg_all;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0:
                    return "Daily";
                case 1:
                    return "Weekly";
                case 2:
                    return "Monthly";
                case 3:
                    return "All";
                default:
                    return null;
            }
        }
    }

    public void setCurrent_date(Calendar current_date) {
        this.current_date = current_date;
    }
}
