package com.example.btl_android;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.objectClass.Account;
import com.example.btl_android.firebaseHelper.FirebaseHelper;
import com.example.btl_android.recycle.Recycle_Account;

import java.util.List;

public class AccListActivity extends AppCompatActivity {

    private RecyclerView lstAccount;
    private ConstraintLayout back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_list);

        lstAccount = findViewById(R.id.rvListAccount);
        back = findViewById(R.id.backLayout);

        new FirebaseHelper().readData(new FirebaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Account> accounts, List<String> keys) {
                new Recycle_Account().setConfig(lstAccount, AccListActivity.this, accounts, keys);
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}