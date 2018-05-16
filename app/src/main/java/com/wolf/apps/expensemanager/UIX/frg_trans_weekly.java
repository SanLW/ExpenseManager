package com.wolf.apps.expensemanager.UIX;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wolf.apps.expensemanager.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class frg_trans_weekly extends Fragment {
    private Calendar current_date;

    public frg_trans_weekly() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frg_trans_weekly, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView txt_income, txt_expense, txt_total, txt_income_amount, txt_expense_amount, txt_total_amount;
        txt_income = (TextView) view.findViewById(R.id.frg_trans_daily_txt_income);
        txt_income_amount = (TextView) view.findViewById(R.id.frg_trans_daily_txt_income_amount);
        txt_expense = (TextView) view.findViewById(R.id.frg_trans_daily_txt_expense);
        txt_expense_amount = (TextView) view.findViewById(R.id.frg_trans_daily_txt_expense_amount);
        txt_total = (TextView) view.findViewById(R.id.frg_trans_daily_txt_total_amount);
        txt_total_amount = (TextView) view.findViewById(R.id.frg_trans_daily_txt_total_amount);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int x;
        x = displayMetrics.widthPixels;

        txt_income.setLayoutParams(new LinearLayout.LayoutParams(x/3, LinearLayout.LayoutParams.WRAP_CONTENT));
        txt_expense.setLayoutParams(new LinearLayout.LayoutParams(x/3, LinearLayout.LayoutParams.WRAP_CONTENT));
        txt_total.setLayoutParams(new LinearLayout.LayoutParams(x/3, LinearLayout.LayoutParams.WRAP_CONTENT));
        txt_income_amount.setLayoutParams(new LinearLayout.LayoutParams(x/3, LinearLayout.LayoutParams.WRAP_CONTENT));
        txt_expense_amount.setLayoutParams(new LinearLayout.LayoutParams(x/3, LinearLayout.LayoutParams.WRAP_CONTENT));
        txt_total_amount.setLayoutParams(new LinearLayout.LayoutParams(x/3, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public void setCurrent_date(Calendar current_date) {
        this.current_date = current_date;
    }
}
