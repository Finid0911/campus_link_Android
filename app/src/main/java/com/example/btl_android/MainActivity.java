package com.example.btl_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public  ArrayList<Account> AccountList;
    private AccountAdapter accAdapter;
    private Button addAcc, showMore;
    private ListView lstAcc;
    private FloatingActionButton addTransaction;
    int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addAcc = findViewById(R.id.addAccBtn);
        showMore = findViewById(R.id.showMoreBtn);
        lstAcc = findViewById(R.id.lvAccount);
        addTransaction = findViewById(R.id.faTransaction);

        AccountList = new ArrayList<>();
        AccountList.add(new Account(1, "Bank", 1000000));
        AccountList.add(new Account(2, "Wallet", 5400000));

        accAdapter = new AccountAdapter(AccountList, this);
        lstAcc.setAdapter(accAdapter);
        registerForContextMenu(lstAcc);

        //
        lstAcc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                selectedID = i;
                return false;
            }
        });

        // add new account
        addAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccDetailActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        //add new transaction
        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
                Gson gson = new Gson();
                String jsonAccount = gson.toJson(AccountList);
                intent.putExtra("list", jsonAccount);
                startActivityForResult(intent, 200);
            }
        });

        //Send data to List Account Activity
        Intent intentListAcc = new Intent(MainActivity.this, AccListActivity.class);
        Bundle bd = new Bundle();
        //intentListAcc.putExtra("listData", AccountList);
        //bd.putSerializable("listData", (Serializable) AccountList);
        //intentListAcc.putExtra("bundle", ArrayList<Account>);
        //startActivity(intentListAcc);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Bundle b = data.getExtras();
            int id = b.getInt("Id");
            System.out.println(id);
            String name = b.getString("AccountName");
            float money = b.getFloat("AccountMoney");
            Account newAccount = new Account(id, name, money);
            if(requestCode == 100 && resultCode == 150){
                AccountList.add(newAccount);
            }
            /*else if(requestCode == 100 && resultCode == 150){
                //ContactList.set(selectedID, newContact);
                int contactID = ContactList.get(selectedID).getId();
                db.updateContact(contactID, newContact);
                ContactList = db.getAllContact();
                ListAdapter.setData(ContactList);
            }*/
            lstAcc.setAdapter(accAdapter);
            accAdapter.notifyDataSetChanged();
        }
    }
}