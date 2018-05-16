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

/**
 * A simple {@link Fragment} subclass.
 */
public class frg_trans extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

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
    }

    class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return new frg_trans_daily();
                case 1:
                    return new frg_trans_weekly();
                case 2:
                    return new frg_trans_monthly();
                case 3:
                    return new frg_trans_all();
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
}
