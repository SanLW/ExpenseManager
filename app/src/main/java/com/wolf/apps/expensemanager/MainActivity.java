package com.wolf.apps.expensemanager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;


import com.wolf.apps.expensemanager.UIX.*;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private TabLayout tabLayout;
    private Fragment frg_transactions, frg_statistics, frg_acs;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.tb_main));

        tabLayout = (TabLayout) findViewById(R.id.tabs_main);

        tabLayout.setOnTabSelectedListener(this);

        frg_transactions = new frg_trans();
        frg_statistics = new frg_stats();
        frg_acs = new frg_accounts();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frm_main, frg_transactions);
        fragmentTransaction.add(R.id.frm_main, frg_statistics);
        fragmentTransaction.add(R.id.frm_main, frg_acs);
        fragmentTransaction.hide(frg_statistics);
        fragmentTransaction.hide(frg_acs);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mn_tb_main, menu);
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch(tab.getPosition()){
            case 0:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).show(frg_transactions).commit();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(frg_statistics).commit();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(frg_acs).commit();
                return;
            case 1:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(frg_transactions).commit();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).show(frg_statistics).commit();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(frg_acs).commit();
                return;
            case 2:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(frg_transactions).commit();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(frg_statistics).commit();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).show(frg_acs).commit();
                return;
            default:
                return;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
