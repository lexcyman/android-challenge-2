package com.mobdeve.mangunea.androidchallenge2;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;

public class MainActivity extends AppCompatActivity {

    private final Random random = new Random();
    private final String[] names = {"Guts", "Casca", "Griffith", "Rickert", "Judeau", "Corkus", "Pippen"};
    private final String[] orderIds = {"100-ROB-421-341", "100-SNY-125-125", "100-ASA-902-734", "100-WOW-101-010"};
    private final String[] itemNames = {"Wonder Machine", "Thingamabob 2000", "Giszo Dizmo", "Behelit necklace fashion smthn"};
    private final String[] itemPrices = {"3499", "139", "250", "500"};

    private ArrayList<OrderList> orderList;
    private ArrayList<OrderList> tempList = new ArrayList<>();
    private OrderListAdapter orderListAdapter = new OrderListAdapter(tempList);
    private ScheduledExecutorService ses;
    private int currPos;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        generateData();
        setAdapter();
    }

    private void generateData() {
        orderList = new ArrayList<>();
        for(int i=0; i<999; i++) {
            String name = randomSelectFromData(names);
            String id = randomSelectFromData(orderIds);
            String item = randomSelectFromData(itemNames);
            String price = randomSelectFromData(itemPrices);
            String date = generateDateString();

            orderList.add(i, new OrderList(name, date, id, item, price));
        }
    }

    private void setAdapter() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(orderListAdapter);

        orderListAdapter.setOnItemClickListener((int position) -> {
            orderList.remove(position);
            orderListAdapter.notifyItemRemoved(position);
        });

        OrderListRunnable runnable = new OrderListRunnable(9999);
        new Thread(runnable).start();
    }

    private String randomSelectFromData(String[] data) {
        return data[this.random.nextInt(data.length)];
    }

    private String generateDateString() {
        Locale philippineLocale = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            philippineLocale = new Locale.Builder().setLanguage("en").setRegion("PH").build();
        }
        return Calendar.getInstance(philippineLocale).getTime().toString();
    }

    class OrderListRunnable implements Runnable {
        int seconds;
        OrderListRunnable(int seconds) {
            this.seconds = seconds;
        }
        @Override
        public void run() {
            for (int i=0; i<seconds; i++) {
                if(i%4 == 0 && i!=0) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            tempList.add(0, orderList.get(currPos));
                            currPos++;
                            orderListAdapter.notifyDataSetChanged();
                        }
                    });
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}