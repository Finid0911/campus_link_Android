package com.example.btl_android;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AccListActivity extends AppCompatActivity {

    private ListView accList;
    private ImageView backImg;
    public static ArrayList<Account> AccountList;
    private accountAdapter accAdapter;
    int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_list);

        backImg = findViewById(R.id.backBtn);
        accList = findViewById(R.id.lstAccount);

        AccountList = new ArrayList<>();
        AccountList.add(new Account(1, "Bank", 1000000f));
        AccountList.add(new Account(2, "Wallet", 500000f));

        accAdapter = new accountAdapter(AccountList, this);
        accList.setAdapter(accAdapter);
        registerForContextMenu(accList);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}