package com.example.btl_android;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {

    public static ArrayList<Account> AccountList;
    private TextView account, category;
    private ImageView exit, confirm;
    private EditText moneyInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        account = findViewById(R.id.txtAccountContent);
        category = findViewById(R.id.txtCategoryContent);
        exit = findViewById(R.id.exitImgView);
        confirm = findViewById(R.id.confirmImgView);
        moneyInput = findViewById(R.id.edtMoneyInput);
        moneyInput.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(moneyInput, InputMethodManager.SHOW_IMPLICIT);

        // Get data of Account from Main Activity
        AccountList = new ArrayList<>();
        String accData = getIntent().getStringExtra("list");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Account>>(){}.getType();
        AccountList = gson.fromJson(accData, type);
        // test
        for(Account ac: AccountList){
            System.out.println(ac.getAccount_name());
        }

        account.setOnClickListener(v -> {
            Intent intentAcc = new Intent(TransactionActivity.this, AccListActivity.class);
            // Send data of Account to Account List Activity
            Gson gson1 = new Gson();
            String jsonAccount = gson1.toJson(AccountList);
            intentAcc.putExtra("list1", jsonAccount);
            startActivityForResult(intentAcc, 101);
        });

        category.setOnClickListener(v ->{
            Intent intentCate = new Intent(TransactionActivity.this, CategoryListActivity.class);
            startActivityForResult(intentCate, 102);
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}