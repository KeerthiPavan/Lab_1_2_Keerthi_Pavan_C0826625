package com.example.lab_1_2_keerthi_pavan_c0826625;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private static final String DB_NAME = "PRODUCTS.db";
    private static final String TABLE_PRODUCTS = "PRODUCTS";
    private static final String PRO_NAME = "NAME";
    private static final String PRO_DESCRIPTION = "DESCRIPTION";
    private static final String PRO_PRICE = "PRICE";
    private static final String PRO_LATITUDE = "LATITUDE";
    private static final String PRO_LONGITUDE = "LONGITUDE";
    private static final String PRO_ID = "ID";
    public DBhelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS + "(" + PRO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PRO_NAME + " TEXT," + PRO_DESCRIPTION + " TEXT," + PRO_PRICE + " INTEGER," + PRO_LATITUDE + " INTEGER," + PRO_LONGITUDE + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PRODUCTS);
        onCreate(db);
    }

    public Boolean insertProductData(Integer ID, String NAME, String DESCRIPTION, Integer PRICE, Integer LATITUDE, Integer LONGITUDE){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRO_ID,ID);
        values.put(PRO_NAME, NAME);
        values.put(PRO_DESCRIPTION, DESCRIPTION);
        values.put(PRO_PRICE, PRICE);
        values.put(PRO_LATITUDE, LATITUDE);
        values.put(PRO_LONGITUDE, LONGITUDE);
        long result = db.insert(TABLE_PRODUCTS, null, values);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PRODUCTS", null);
        return cursor;
    }




}
