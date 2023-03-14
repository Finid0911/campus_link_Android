package com.example.btl_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TransactionActivity extends AppCompatActivity {

    private TextView account,  category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        account = findViewById(R.id.txtAccountContent);
        category = findViewById(R.id.txtCategoryContent);

        account.setOnClickListener(v -> {
            Intent intent = new Intent(TransactionActivity.this, AccListActivity.class);
            startActivityForResult(intent, 101);
        });



    }
}