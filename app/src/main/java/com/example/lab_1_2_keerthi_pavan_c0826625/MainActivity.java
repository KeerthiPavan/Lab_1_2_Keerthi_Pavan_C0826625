package com.example.lab_1_2_keerthi_pavan_c0826625;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBhelper db;
    TextView proId, proPrice, proName, proDesc;
    Button btnPrevious, btnNext;
    List<Products> mProducts = new ArrayList<>();

    int productDisplaying = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proId =  findViewById(R.id.pid);
        proPrice = findViewById(R.id.price);
        proName = findViewById(R.id.name);
        proDesc = findViewById(R.id.desc);
        btnNext = findViewById(R.id.next);
        btnPrevious = findViewById(R.id.previous);

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


    }
}