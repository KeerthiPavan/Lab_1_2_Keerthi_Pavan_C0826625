package com.example.lab_1_2_keerthi_pavan_c0826625;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBhelper db;
    TextView tv;
    EditText proId, proPrice, proName, proDesc;
    Button btnPrevious, btnNext, btnUpdate, btnDelete, btnAddTask;
    List<Products> mProducts = new ArrayList<>();

    int productDisplaying = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proId = findViewById(R.id.pid);
        proPrice = findViewById(R.id.price);
        proName = findViewById(R.id.name);
        proDesc = findViewById(R.id.desc);
        btnNext = findViewById(R.id.next);
        btnPrevious = findViewById(R.id.previous);
        btnUpdate = findViewById(R.id.update);
        btnDelete = findViewById(R.id.delete);
        btnAddTask = findViewById(R.id.addProduct);
        tv = findViewById(R.id.textview);

        db = new DBhelper(this);
        data();
    }


    public void data(){

        //Toast.makeText(MainActivity.this, "im here", Toast.LENGTH_SHORT).show();
        db = new DBhelper(this);
        db.insertProductData(10001,"MacBook","The MacBook is a brand of Macintosh notebook computers designed and marketed by Apple Inc. that use Apple's macOS operating system since 2006.",1100,44,33);
        db.insertProductData(10002,"Ipad","The iPad is a brand of iOS and iPadOS-based tablet computers developed by Apple Inc. It is the successor of the Newton MessagePad and an unreleased prototype.",450,34,3);
        db.insertProductData(10003,"AppleWatch","The Apple Watch was released in April 2015[18][19] and quickly became the best-selling wearable device: 4.2 million were sold in the second quarter of fiscal 2015",500,43,3);
        db.insertProductData(10004,"iphone13","The iPhone 13  is a line of smartphones designed and marketed by Apple Inc. that use Apple's iOS mobile operating system. ",1100,44,33);
        db.insertProductData(10005,"iphone13Pro","The iPhone 13pro  is a line of smartphones designed and marketed by Apple Inc. that use Apple's iOS mobile operating system. ",1100,44,33);
        db.insertProductData(10006,"iphone13ProMax","The iPhone 13pro max  is a line of smartphones designed and marketed by Apple Inc. that use Apple's iOS mobile operating system. ",1100,44,33);
        db.insertProductData(10007,"iphone12","The iPhone 12  is a line of smartphones designed and marketed by Apple Inc. that use Apple's iOS mobile operating system. ",1100,44,33);
        db.insertProductData(10008,"iphone12Pro","The iPhone 12 pro  is a line of smartphones designed and marketed by Apple Inc. that use Apple's iOS mobile operating system.",1100,44,33);

//        if(productAdded) {
//            Toast.makeText(MainActivity.this, "Im inserted", Toast.LENGTH_SHORT).show();
//            return;
//        }

        Cursor res = db.getdata();
        if (res.getCount() == 0) {
            Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }

        while (res.moveToNext()) {
            Log.d("myTag", "Im adding Values" );
            Products mProduct = new Products(res.getInt(0), res.getString(1), res.getString(2), res.getInt(3), res.getInt(4), res.getInt(5));
            mProducts.add(mProduct);
        }
        Log.d("myTag", String.valueOf(mProducts.size()) );
        productDisplaying = 0;
        proId.setText(String.valueOf(mProducts.get(productDisplaying).getId()));
        proName.setText(String.valueOf(mProducts.get(productDisplaying).getName()));
        proDesc.setText(String.valueOf(mProducts.get(productDisplaying).getDescription()));
        proPrice.setText(String.valueOf(mProducts.get(productDisplaying).getPrice()));
        Log.d("myTag", String.valueOf(mProducts.get(productDisplaying).getId()) );

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proName.setEnabled(false);
                proDesc.setEnabled(false);
                proPrice.setEnabled(false);
                btnUpdate.setText("EDIT PRODUCT");
                if(mProducts.size()>productDisplaying+1){
                    productDisplaying += 1;
                    proId.setText(String.valueOf(mProducts.get(productDisplaying).getId()));
                    proName.setText(String.valueOf(mProducts.get(productDisplaying).getName()));
                    proDesc.setText(String.valueOf(mProducts.get(productDisplaying).getDescription()));
                    proPrice.setText(String.valueOf(mProducts.get(productDisplaying).getPrice()));

                }
                else{
                    Toast.makeText(MainActivity.this, "Last Product", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proName.setEnabled(false);
                proDesc.setEnabled(false);
                proPrice.setEnabled(false);
                btnUpdate.setText("EDIT PRODUCT");
                if(0 < productDisplaying){
                    productDisplaying -= 1;
                    proId.setText(String.valueOf(mProducts.get(productDisplaying).getId()));
                    proName.setText(String.valueOf(mProducts.get(productDisplaying).getName()));
                    proDesc.setText(String.valueOf(mProducts.get(productDisplaying).getDescription()));
                    proPrice.setText(String.valueOf(mProducts.get(productDisplaying).getPrice()));

                }
                else{
                    Toast.makeText(MainActivity.this, "No Previous Product", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int a =  Integer.parseInt(btnUpdate.getTag().toString());
                Log.d("myTag", String.valueOf(a) );
                if(a == 0) {
                    tv.setText("EDIT THIS PRODUCT");
                    btnUpdate.setText("UPDATE PRODUCT");
                    btnDelete.setVisibility(View.GONE);
                    btnNext.setVisibility(View.GONE);
                    btnPrevious.setVisibility(View.GONE);
                    btnAddTask.setVisibility(View.GONE);
                    proName.setEnabled(true);
                    proDesc.setEnabled(true);
                    proPrice.setEnabled(true);
                    Toast.makeText(MainActivity.this, "Update Product details and press Update.", Toast.LENGTH_SHORT).show();
                    btnUpdate.setTag(1);
                }
                else{
                    String idTXT = proId.getText().toString();
                    String nameTXT = proName.getText().toString();
                    String descTXT = proDesc.getText().toString();
                    String priceTXT = proPrice.getText().toString();
                    boolean result = db.updatedata(idTXT, nameTXT, descTXT, priceTXT);

                    if(result){
                        Log.d("Database","Updated");
                        Toast.makeText(MainActivity.this, "Product Details Updated Successfully", Toast.LENGTH_SHORT).show();
                        btnUpdate.setTag(0);
                        tv.setText("PRODUCTS");
                        btnUpdate.setText("EDIT Product");
                        proName.setEnabled(false);
                        proDesc.setEnabled(false);
                        proPrice.setEnabled(false);
                        btnDelete.setVisibility(View.VISIBLE);
                        btnNext.setVisibility(View.VISIBLE);
                        btnPrevious.setVisibility(View.VISIBLE);
                        btnAddTask.setVisibility(View.VISIBLE);


                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idTXT = proId.getText().toString();
                Boolean checkdeletedata = db.deletedata(idTXT);

                if(checkdeletedata){
                    int i = 0;
                    for(Products p: mProducts){
                        if(String.valueOf(p.getId()).equals(idTXT)){
                            proName.setEnabled(false);
                            proDesc.setEnabled(false);
                            proPrice.setEnabled(false);
                            btnUpdate.setText("EDIT PRODUCT");
                            if(mProducts.size()>productDisplaying+1){
                                productDisplaying += 1;
                                proId.setText(String.valueOf(mProducts.get(productDisplaying).getId()));
                                proName.setText(String.valueOf(mProducts.get(productDisplaying).getName()));
                                proDesc.setText(String.valueOf(mProducts.get(productDisplaying).getDescription()));
                                proPrice.setText(String.valueOf(mProducts.get(productDisplaying).getPrice()));
                            }
                            else{
                                proName.setText("");
                                proDesc.setText("");
                                proPrice.setText("");
                                proId.setText("");
                                //Toast.makeText(MainActivity.this, "Last Product", Toast.LENGTH_SHORT).show();
                            }
                            mProducts.remove(i);

                            break;

                        }
                        i++;
                    }
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }

        });
            btnAddTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                int a =  Integer.parseInt(btnAddTask.getTag().toString());
                Log.d("myTag", String.valueOf(a) );
                if(a == 0) {
                    tv.setText("Add New Product");
                    btnUpdate.setVisibility(View.GONE);
                    btnDelete.setVisibility(View.GONE);
                    btnNext.setVisibility(View.GONE);
                    btnPrevious.setVisibility(View.GONE);
                    btnAddTask.setText("SAVE PRODUCT");
                    proId.setText("");
                    proName.setText("");
                    proDesc.setText("");
                    proPrice.setText("");
                    proId.setEnabled(true);
                    proName.setEnabled(true);
                    proDesc.setEnabled(true);
                    proPrice.setEnabled(true);
                    Toast.makeText(MainActivity.this, "Add Product details and press Add.", Toast.LENGTH_SHORT).show();
                    btnAddTask.setTag(1);
                }else{
                    Integer idTXT = Integer.valueOf(proId.getText().toString());
                    String nameTXT = proName.getText().toString();
                    String descTXT = proDesc.getText().toString();
                    Integer priceTXT = Integer.valueOf(proPrice.getText().toString());
                    if(idTXT != null && nameTXT != null && descTXT != null && priceTXT != null){
                        boolean result = db.insertProductData(idTXT,nameTXT,descTXT,priceTXT,44,33);
                        if(result){
                            Toast.makeText(MainActivity.this, "Product Details Added Successfully", Toast.LENGTH_SHORT).show();
                            btnAddTask.setTag(0);
                            btnAddTask.setText("ADD NEW PRODUCT");
                            proId.setEnabled(false);
                            proName.setEnabled(false);
                            proDesc.setEnabled(false);
                            proPrice.setEnabled(false);
                            btnDelete.setVisibility(View.VISIBLE);
                            btnNext.setVisibility(View.VISIBLE);
                            btnPrevious.setVisibility(View.VISIBLE);
                            btnUpdate.setVisibility(View.VISIBLE);


                        }

                    }else{
                        Toast.makeText(MainActivity.this, "Fill All Fields", Toast.LENGTH_SHORT).show();
                    }


                }


                }

            });

    }


}