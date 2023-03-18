package com.example.btl_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public  ArrayList<Account> AccountList;
    private TextView addAcc, showMore;
    private RecyclerView lstAcc, lstTrans;
    private TableLayout cate1, cate2, cate3, cate4, cate5, cate6, cate7, cate8;
    private TextView t1, t2, t3, t4;
    public static String text;
    int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addAcc = findViewById(R.id.addBtn);
        showMore = findViewById(R.id.moreTV);
        lstAcc = findViewById(R.id.rvAccount);
        lstTrans = findViewById(R.id.rvTransaction);
        //lstTrans = findViewById(R.id.lvTransaction);
        cate1 = findViewById(R.id.cate1);
        cate2 = findViewById(R.id.cate2);
        cate3 = findViewById(R.id.cate3);
        cate4 = findViewById(R.id.cateMore);
        /*cate5 = findViewById(R.id.cate5);
        cate6 = findViewById(R.id.cate6);*/

        t1 = findViewById(R.id.shoppingTxt);

        new FirebaseHelper().readData(new FirebaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Account> accounts, List<String> keys) {
                new Recycle().setConfig(lstAcc, MainActivity.this, accounts, keys);
            }

            @Override
            public void DataIsInsert() {

            }

            @Override
            public void DataIsUpdate() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

        new FirebaseHelper_Transaction().readData(new FirebaseHelper_Transaction.DataStatus() {
            @Override
            public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {
                new Recycle_Transaction().setConfig(lstTrans, MainActivity.this, transactions, keys);
            }

            @Override
            public void DataIsInsert() {

            }

            @Override
            public void DataIsUpdate() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

        /*AccountList = new ArrayList<>();
        AccountList.add(new Account(1, "Bank", 1000000));
        AccountList.add(new Account(2, "Wallet", 5400000));

        accAdapter = new AccountAdapter(AccountList, this);
        lstAcc.setAdapter(accAdapter);
        registerForContextMenu(lstAcc);*/

        //
        /*lstAcc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                selectedID = i;
                return false;
            }
        });*/

        // add new account
        addAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccDetailActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        cate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = t1.getText().toString();
                Intent intent = new Intent(MainActivity.this, AccListActivity.class);
                Gson gson = new Gson();
                String jsonAccount = gson.toJson(AccountList);
                intent.putExtra("list", jsonAccount);
                startActivityForResult(intent, 100);
            }
        });

        //add new transaction
        /*addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
                Gson gson = new Gson();
                String jsonAccount = gson.toJson(AccountList);
                intent.putExtra("list", jsonAccount);
                startActivityForResult(intent, 200);
            }
        });*/

        //Send data to List Account Activity
        Intent intentListAcc = new Intent(MainActivity.this, AccListActivity.class);
        Bundle bd = new Bundle();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            /*Bundle b = data.getExtras();
            int id = b.getInt("Id");
            System.out.println(id);
            String name = b.getString("AccountName");
            float money = b.getFloat("AccountMoney");*/
            /*Account newAccount = new Account(id, name, money);
            if(requestCode == 100 && resultCode == 150){
                AccountList.add(newAccount);
            }
            lstAcc.setAdapter(accAdapter);
            accAdapter.notifyDataSetChanged();*/
        }
    }
}