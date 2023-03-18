package com.example.btl_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public  ArrayList<Account> AccountList;
    private TextView addAcc, showMore;
    private RecyclerView lstAcc, lstTrans;
    private TableLayout cate1, cate2, cate3, cate4, cate5, cate6, cateMore, cateIncome;
    private TextView t1, t2, t3, t4, t5, t6, t7, t8;
    public static String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addAcc = findViewById(R.id.editBtn);
        showMore = findViewById(R.id.moreTV);
        lstAcc = findViewById(R.id.rvAccount);
        lstTrans = findViewById(R.id.rvTransaction);

        //category in main activity
        cate1 = findViewById(R.id.cate1);
        cate2 = findViewById(R.id.cate2);
        cate3 = findViewById(R.id.cate3);
        cate4 = findViewById(R.id.cateMore);
        cate5 = findViewById(R.id.cate5);
        cate6 = findViewById(R.id.cate6);
        cateMore = findViewById(R.id.cateMore);
        cateIncome = findViewById(R.id.income);

        // text of actegories
        t1 = findViewById(R.id.shoppingTxt);
        t2 = findViewById(R.id.vehicleTxt);
        t3 = findViewById(R.id.foodTxt);
        t4 = findViewById(R.id.electricTxt);
        t5 = findViewById(R.id.waterTxt);
        t6 = findViewById(R.id.fuelTxt);
        t7 = findViewById(R.id.moreTV);
        t8 = findViewById(R.id.incomeTxt);

        new FirebaseHelper().readData(new FirebaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Account> accounts, List<String> keys) {
                new Recycle2().setConfig(lstAcc, MainActivity.this, accounts, keys);
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

        // add new account
        addAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddAccountActivity.class);
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

    }

}