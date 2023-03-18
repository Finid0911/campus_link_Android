package com.example.btl_android;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {

    public static ArrayList<Account> AccountList;
    public TextView account, category;
    private ImageView exit, confirm;
    private EditText moneyInput;
    private String key, name;
    private TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        account = findViewById(R.id.txtAcc);
        category = findViewById(R.id.txtCate);
        exit = findViewById(R.id.exitImgView);
        confirm = findViewById(R.id.confirmImgView);

        key = getIntent().getStringExtra("key");
        //id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        //money = getIntent().getStringExtra("money");

        account.setText(String.valueOf(name));
        category.setText(MainActivity.text);

        Calendar calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        // Hiển thị ngày hiện tại trên một TextView
        txtTime = findViewById(R.id.txtTime);
        String currentDate = String.format("%02d/%02d/%d", day, month, year);
        txtTime.setText(currentDate);

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ngày hiện tại của hệ thống
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(TransactionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Lấy ngày được chọn và hiển thị nó trên TextView
                                String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                                txtTime.setText(selectedDate);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
                System.out.println(txtTime.getText().toString());
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}