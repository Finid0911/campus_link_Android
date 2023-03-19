package com.example.btl_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_android.basicClass.Account;
import com.example.btl_android.firebaseHelper.FirebaseHelper;

import java.util.List;

public class AddAccountActivity extends AppCompatActivity {

    private EditText accName, accMoney;
    private Button addBtn, cancelBtn;
    private static int accId = 1;
    private static String accID = "10";

    /*public int autoSetID(int id){
        boolean check = false;
        do{
            ArrayList<Account> ar = db.select(id);
            if(ar.size() == 0){
                check = true;
            }
            else{
                id += 1;
            }
        } while(check == false);
        return id;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        accName = findViewById(R.id.edtCateName);
        accMoney = findViewById(R.id.edtMoney);
        addBtn = findViewById(R.id.delBtn);
        cancelBtn = findViewById(R.id.cancelBtn);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account();
                //account.setId(EditId.getText().toString());
                account.setAccount_name(accName.getText().toString());
                account.setMoney(accMoney.getText().toString());
                new FirebaseHelper().addData(account, new FirebaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Account> accounts, List<String> keys) {

                    }

                    @Override
                    public void DataIsInsert() {
                        Toast.makeText(AddAccountActivity.this, "Add successfully", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsUpdate() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
                finish(); return;
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}