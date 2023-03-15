package com.example.btl_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AccListActivity extends AppCompatActivity {

    private ListView accList;
    private ImageView backImg;
    public static ArrayList<Account> AccountList;
    private AccountAdapter accAdapter;
    int selectedID;
    MainActivity ac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_list);

        backImg = findViewById(R.id.backBtn);
        accList = findViewById(R.id.lstCategory);

        AccountList = new ArrayList<>();
        String Acccount = getIntent().getStringExtra("list1");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Account>>(){}.getType();
        AccountList = gson.fromJson(Acccount, type);
        for(Account ac: AccountList){
            System.out.println(ac.getAccount_name());
        }

        accAdapter = new AccountAdapter(AccountList, this);
        accList.setAdapter(accAdapter);
        registerForContextMenu(accList);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        accList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                selectedID = i;
                Account c;
            }
        });


    }
}