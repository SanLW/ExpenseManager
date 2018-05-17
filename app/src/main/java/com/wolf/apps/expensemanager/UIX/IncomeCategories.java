package com.wolf.apps.expensemanager.UIX;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wolf.apps.expensemanager.Models.*;

import com.wolf.apps.expensemanager.R;

import java.util.ArrayList;

public class IncomeCategories extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private ArrayList<IncomeCategory> incomeCategories;
    private IncomeCategory current_income_category;
    private ExpenseDbManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_categories);
        setSupportActionBar((Toolbar) findViewById(R.id.tb_categories));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Income Categories");
        incomeCategories = new ArrayList<>();
        db = new ExpenseDbManager(this);
        incomeCategories = IncomeCategory.getAll(db.getReadableDatabase());
        recyclerView = (RecyclerView)findViewById(R.id.rcv_income_categories);
        recyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new RecyclerAdapter());

        ((FloatingActionButton) findViewById(R.id.fab_categories)).setOnClickListener(
                new FloatingActionButton.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        new IncomeCategoryDialog(IncomeCategories.this, true, 0, "").show();

                    }
                }
        );
    }

    class IncomeCategoryDialog extends Dialog {

        Button btn_add, btn_cancel;
        EditText txt_name;

        public IncomeCategoryDialog(@NonNull Context context, final boolean mode, final int position, final String current_name) {
            super(context);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setCancelable(true);
            setContentView(R.layout.fragment_frg_name);

            WindowManager.LayoutParams l_params = this.getWindow().getAttributes();
            l_params.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            l_params.width = WindowManager.LayoutParams.MATCH_PARENT;
            this.getWindow().setAttributes(l_params);

            btn_add = (Button) findViewById(R.id.btn_add);
            btn_cancel = (Button) findViewById(R.id.btn_cancel);
            txt_name = (EditText) findViewById(R.id.txt_name);

            if(mode == false){
                txt_name.setText(current_name);
            }

            btn_add.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!txt_name.getText().toString().trim().equals("")){
                                if(mode == true){
                                    IncomeCategory temp = new IncomeCategory(0, txt_name.getText().toString());
                                    temp.add(db.getWritableDatabase());
                                    incomeCategories.add(temp);
                                    cancel();
                                }
                                else {
                                    IncomeCategory temp = incomeCategories.get(position);
                                    temp.setDescription(txt_name.getText().toString());
                                    temp.update(db.getWritableDatabase());
                                    incomeCategories.remove(position);
                                    incomeCategories.add(position, temp);
                                    cancel();
                                }
                            }
                        }
                    }
            );

            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                }
            });
        }
    }


    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View contactView = inflater.inflate(R.layout.category_holder, parent, false);

            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
                holder.incomeCategory = (incomeCategories.get(position));
                holder.txt_name.setText(holder.incomeCategory.getDescription());

                holder.btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        incomeCategories.get(position).remove(db.getWritableDatabase());
                        incomeCategories.remove(position);
                        notifyDataSetChanged();
                    }
                });

                holder.btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IncomeCategory temp = incomeCategories.get(holder.getPosition());
                        new IncomeCategoryDialog(IncomeCategories.this, false, holder.getPosition(), temp.getDescription()).show();
                        notifyDataSetChanged();
                    }
                });
        }

        @Override
        public int getItemCount() {
            return incomeCategories.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            IncomeCategory incomeCategory;
            TextView txt_name;
            ImageButton btn_list, btn_edit, btn_delete;

            public ViewHolder(View v) {
                super(v);
                txt_name = v.findViewById(R.id.txt_cat_name);
                btn_list = v.findViewById(R.id.btn_sub_categories);
                btn_edit = v.findViewById(R.id.btn_edit);
                btn_delete = v.findViewById(R.id.btn_delete);
            }
        }
    }



}
