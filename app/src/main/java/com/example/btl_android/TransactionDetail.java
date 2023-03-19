package com.example.btl_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_android.basicClass.Transaction;
import com.example.btl_android.firebaseHelper.FirebaseHelper_Transaction;

import java.util.List;

public class TransactionDetail extends AppCompatActivity {

    private EditText accountTxt, cateTxt, moneyTxt, dateTxt;
    private Button cancelBtn, deleteBtn;
    private String account, category, money, date, key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);

        accountTxt = findViewById(R.id.edtAccount);
        cateTxt = findViewById(R.id.edtCateName);
        moneyTxt = findViewById(R.id.edtMoneyIn);
        dateTxt = findViewById(R.id.edtDate);
        cancelBtn = findViewById(R.id.cancelBtn);
        deleteBtn = findViewById(R.id.delBtn);

        key = getIntent().getStringExtra("key");
        account = getIntent().getStringExtra("name");
        category = getIntent().getStringExtra("category");
        money = getIntent().getStringExtra("money");
        date = getIntent().getStringExtra("date");

        accountTxt.setText(account);
        cateTxt.setText(category);
        moneyTxt.setText(money);
        dateTxt.setText(date);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseHelper_Transaction().deleteData(key, new FirebaseHelper_Transaction.DataStatus() {
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
            }
        });
    }
}
