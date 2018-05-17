package com.wolf.apps.expensemanager;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CalendarView;
import com.wolf.apps.expensemanager.UIX.*;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private Calendar current_date;
    private frg_trans calling_frg_trans;
    private frg_stats calling_frg_stats;
    private frg_accounts calling_frg_accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.tb_main));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs_main);
        tabLayout.setOnTabSelectedListener(this);

        calling_frg_trans = new frg_trans();
        calling_frg_stats = new frg_stats();
        calling_frg_accounts = new frg_accounts();
        calling_frg_trans.setCurrent_date(current_date);
        calling_frg_stats.setCurrent_date(current_date);
        calling_frg_accounts.setCurrent_date(current_date);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frm_main, calling_frg_trans);
        fragmentTransaction.add(R.id.frm_main, calling_frg_stats);
        fragmentTransaction.add(R.id.frm_main, calling_frg_accounts);
        fragmentTransaction.hide(calling_frg_stats);
        fragmentTransaction.hide(calling_frg_accounts);
        fragmentTransaction.commit();

        current_date = Calendar.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mn_tb_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.mn_tb_main_calendar:
                new CalendarDialog(this).show();
                return true;
            case R.id.mn_tb_main_settings:
                return true;
            case R.id.mn_tb_main_settings_income_categories:
                startActivity(new Intent(this, IncomeCategories.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch(tab.getPosition()){
            case 0:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).show(calling_frg_trans).commit();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(calling_frg_stats).commit();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(calling_frg_accounts).commit();
                return;
            case 1:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(calling_frg_trans).commit();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).show(calling_frg_stats).commit();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(calling_frg_accounts).commit();
                return;
            case 2:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(calling_frg_trans).commit();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).hide(calling_frg_stats).commit();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).show(calling_frg_accounts).commit();
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

    class CalendarDialog extends Dialog{

        public CalendarDialog(@NonNull Context context) {
            super(context);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setCancelable(true);
            setContentView(R.layout.fragment_frg_calendar_dialog);

            CalendarView calendarView = findViewById(R.id.cld_main);
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                    current_date.set(year, month, dayOfMonth);
                    cancel();
                }
            });

            WindowManager.LayoutParams l_params = this.getWindow().getAttributes();
            l_params.gravity = Gravity.TOP;
            l_params.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            this.getWindow().setAttributes(l_params);
        }


    }

}
