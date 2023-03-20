package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl_android.firebaseHelper.FirebaseHelper_Transaction;
import com.example.btl_android.objectClass.Transaction;

import java.util.List;

public class IncomeDetail extends AppCompatActivity {

    private TextView accountTxt, cateTxt, moneyTxt, dateTxt;
    private Button returnBtn;
    private ImageView deleteBtn;
    private String account, category, money, date, key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_detail);

        accountTxt = findViewById(R.id.accountTxt);
        cateTxt = findViewById(R.id.categoryTxt);
        moneyTxt = findViewById(R.id.moneyTxt);
        dateTxt = findViewById(R.id.dateTxt);
        returnBtn = findViewById(R.id.returnBtn);
        deleteBtn = findViewById(R.id.deleteImg);

        key = getIntent().getStringExtra("key");
        account = getIntent().getStringExtra("name");
        category = getIntent().getStringExtra("category");
        money = getIntent().getStringExtra("money");
        date = getIntent().getStringExtra("date");

        accountTxt.setText(account);
        cateTxt.setText(category);
        moneyTxt.setText(money);
        dateTxt.setText(date);

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseHelper_Transaction().deleteData2(key, new FirebaseHelper_Transaction.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {

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

                finish();
            }
        });
    }
}