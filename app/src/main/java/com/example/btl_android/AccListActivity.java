package com.example.btl_android;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.objectClass.Account;
import com.example.btl_android.firebaseHelper.FirebaseHelper;
import com.example.btl_android.recycle.Recycle;

import java.util.List;

public class AccListActivity extends AppCompatActivity {

    /*private ListView accList;
    private ImageView backImg;
    public static ArrayList<Account> AccountList;
    private AccountAdapter accAdapter;
    int selectedID;*/

    private RecyclerView lstAccount;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_list);

        lstAccount = findViewById(R.id.rvListAccount);
        back = findViewById(R.id.backBtn);

        new FirebaseHelper().readData(new FirebaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Account> accounts, List<String> keys) {
                new Recycle().setConfig(lstAccount, AccListActivity.this, accounts, keys);
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


        /*backImg = findViewById(R.id.backBtn);
        accList = findViewById(R.id.lstCategory);

        // Get data of Account from Transaction Activity
        AccountList = new ArrayList<>();
        String accData = getIntent().getStringExtra("list");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Account>>(){}.getType();
        AccountList = gson.fromJson(accData, type);
        // test
        for(Account ac: AccountList){
            System.out.println(ac.getAccount_name());
        }

        accAdapter = new AccountAdapter(AccountList, this);
        accList.setAdapter(accAdapter);
        registerForContextMenu(accList);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        accList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                selectedID = i;
                Account c = AccountList.get(selectedID);
                Intent intent = new Intent(AccListActivity.this, TransactionActivity.class);
                Bundle b = new Bundle();
                b.putInt("AccId", c.getId());
                b.putString("AccountName", c.getAccount_name());
                b.putFloat("AccountMoney", c.getMoney());
                intent.putExtras(b);
                System.out.println(MainActivity.text);
                System.out.println(c.getAccount_name());
                setResult(200);
                startActivityForResult(intent, 101);
            }
        });*/


    }
}