package com.wolf.apps.expensemanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

class IncomeDetails {
    public enum transaction_types{CASH,CREDIT,DEBIT};

    private int id;
    private float amount;
    private String description;
    private transaction_types tr_type;
    private IncomeSubCategory incomeSubCategory;

    public IncomeDetails(int id, float amount, String description, transaction_types tr_type, IncomeSubCategory incomeSubCategory){
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.tr_type = tr_type;
        this.incomeSubCategory = incomeSubCategory;
    }

    public int getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public transaction_types getTr_type() {
        return tr_type;
    }

    public IncomeSubCategory getIncomeSubCategory() {
        return incomeSubCategory;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTr_type(transaction_types tr_type) {
        this.tr_type = tr_type;
    }

    public void add(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("i_details_description", this.description);
        values.put("i_details_amount", this.amount);
        values.put("transaction_type", this.tr_type.ordinal());
        values.put("i_sub_cat_id", this.incomeSubCategory.getId());
        db.insert("income_details", null, values);
        db.close();
    }

    public void remove(SQLiteDatabase db){
        db.execSQL("DELETE FROM income_details WHERE i_details_id = " + this.id);
    }

    public void update(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put("i_details_amount", this.amount);
        values.put("i_details_description", this.description);
        values.put("transaction_type", this.tr_type.ordinal());
        db.update("income_details", values, "i_details_id = " + this.id, null);
        db.close();
    }

    public static ArrayList<IncomeDetails> getAll(SQLiteDatabase db){
        ArrayList<IncomeDetails>  income_details = new ArrayList<IncomeDetails>();
        Cursor cursor = db.rawQuery("SELECT * FROM income_details", null);
        while(cursor.moveToNext()){
            income_details.add(new IncomeDetails(cursor.getInt(0), cursor.getFloat(1), cursor.getString(2), transaction_types.values()[cursor.getInt(3)], IncomeSubCategory.getById(cursor.getInt(4),db)));
        }
        db.close();
        return  income_details;
    }

    public static IncomeDetails getById(int id, SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT * FROM income_details WHERE i_details_id = " + id, null);
        if(cursor.moveToFirst()){
            return new IncomeDetails(id , cursor.getFloat(1), cursor.getString(2), transaction_types.values()[cursor.getInt(3)], IncomeSubCategory.getById(cursor.getInt(4),db));
        }
        return null;
    }

    public static IncomeDetails getByDescription(String description, SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT * FROM income_details WHERE i_details_description = '" + description + "'", null);
        if(cursor.moveToFirst()){
            return new IncomeDetails(cursor.getInt(0) , cursor.getFloat(1), description, transaction_types.values()[cursor.getInt(3)], IncomeSubCategory.getById(cursor.getInt(4),db));
        }
        return null;
    }

    public static ArrayList<IncomeDetails> getByIncomeSubCategory(IncomeSubCategory category, SQLiteDatabase db){
        ArrayList<IncomeDetails>  income_details = new ArrayList<IncomeDetails>();
        Cursor cursor = db.rawQuery("SELECT * FROM income_details WHERE i_sub_cat_id = " + category.getId(), null);
        while(cursor.moveToNext()){
            income_details.add(new IncomeDetails(cursor.getInt(0), cursor.getFloat(1), cursor.getString(2), transaction_types.values()[cursor.getInt(3)], category));
        }
        db.close();
        return  income_details;
    }

    public static ArrayList<IncomeDetails> getByTransactionType(transaction_types type, SQLiteDatabase db){
        ArrayList<IncomeDetails>  income_details = new ArrayList<IncomeDetails>();
        Cursor cursor = db.rawQuery("SELECT * FROM income_details WHERE transatcion_type = " +  type.ordinal(), null);
        while(cursor.moveToNext()){
            income_details.add(new IncomeDetails(cursor.getInt(0), cursor.getFloat(1), cursor.getString(2), type, IncomeSubCategory.getById(cursor.getInt(4),db)));
        }
        db.close();
        return  income_details;
    }

}
