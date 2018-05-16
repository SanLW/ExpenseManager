package com.wolf.apps.expensemanager.UIX;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wolf.apps.expensemanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class frg_accounts extends Fragment {


    public frg_accounts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frg_accounts, container, false);
    }

}
