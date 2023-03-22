package com.example.btl_android;

import static java.nio.file.Files.delete;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl_android.objectClass.Account;
import com.example.btl_android.firebaseHelper.FirebaseHelper;

import java.util.List;

public class DetailAccount extends AppCompatActivity {

    private EditText accName, accMoney;
    private Button editBtn, cancelBtn;
    private ImageView deleteBtn;
    private String name, money, key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_account);

        accName = findViewById(R.id.edtCateName);
        accMoney = findViewById(R.id.edtMoney);
        editBtn = findViewById(R.id.delBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        deleteBtn = findViewById(R.id.deleteImg);

        key = getIntent().getStringExtra("key");
        name = getIntent().getStringExtra("name");
        money = getIntent().getStringExtra("money");

        accName.setText(name);
        accMoney.setText(money);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account();
                //account.setId(idtxt.getText().toString());
                account.setAccount_name(accName.getText().toString());
                account.setMoney(accMoney.getText().toString());
                new FirebaseHelper().editData(key, account, new FirebaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Account> accounts, List<String> keys) {

                    }

                    @Override
                    public void DataIsInsert() {

                    }

                    @Override
                    public void DataIsUpdate() {
                        Toast.makeText(DetailAccount.this, "Update successfully", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(DetailAccount.this)
                        .setTitle("Are you sure?")
                        .setMessage("If you delete, this account will disapear.")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            delete();
                            dialog.dismiss();
                        })
                        .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                        .show();


            }
        });
    }

    private void delete(){
        new FirebaseHelper().deleteData(key, new FirebaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Account> accounts, List<String> keys) {

            }

            @Override
            public void DataIsInsert() {

            }

            @Override
            public void DataIsUpdate() {

            }

            @Override
            public void DataIsDeleted() {
                finish();
            }
        });
    }
}