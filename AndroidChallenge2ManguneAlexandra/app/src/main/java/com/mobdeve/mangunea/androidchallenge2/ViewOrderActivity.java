package com.mobdeve.mangunea.androidchallenge2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ViewOrderActivity extends AppCompatActivity {

    private TextView viewOrderIdTv;
    private TextView viewOrdererNameTv;
    private TextView viewDateTv;
    private TextView viewItemNameTv;
    private TextView viewItemPriceTv;

    private String name;
    private String date;
    private String id;
    private String item;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        init();
    }

    private void init() {
        this.viewOrderIdTv = (TextView) findViewById(R.id.viewOrderIdTv);
        this.viewOrdererNameTv = (TextView) findViewById(R.id.viewOrdererNameTv);
        this.viewDateTv = (TextView) findViewById(R.id.viewDateTv);
        this.viewItemNameTv = (TextView) findViewById(R.id.viewItemNameTv);
        this.viewItemPriceTv = (TextView) findViewById(R.id.viewItemPriceTv);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        date = intent.getStringExtra("date");
        id = intent.getStringExtra("id");
        item = intent.getStringExtra("item");
        price = intent.getStringExtra("price");

        this.viewOrderIdTv.setText("Order ID: " + id);
        this.viewOrdererNameTv.setText("Orderer's Name: " + name);
        this.viewDateTv.setText("Ordered On: " + date);
        this.viewItemNameTv.setText("Item: " + item);
        this.viewItemPriceTv.setText("Price: " + price);
    }
}