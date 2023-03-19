package com.example.btl_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryDetailActivity extends AppCompatActivity {

    private static String accID = "10";
    private EditText name, icon, background;
    private Button add, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);

        name = findViewById(R.id.edtCateName);
        icon = findViewById(R.id.edtAccount);
        background = findViewById(R.id.edtMoneyIn);
        add = findViewById(R.id.delBtn);
        cancel = findViewById(R.id.cancelBtn);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            int id = bundle.getInt("CateId");
            String cateName = bundle.getString("CateName");
            String cateBG = bundle.getString("Background");
            String cateIcon = bundle.getString("Icon");
            name.setText(String.valueOf(cateName));
            background.setText(String.valueOf(cateBG));
            icon.setText(String.valueOf(cateIcon));
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                Bundle bundle1 = new Bundle();
                int id = Integer.parseInt(accID);
                String cateName = name.getText().toString();
                System.out.println(cateName);
                String cateBG = background.getText().toString();
                String cateIcon = icon.getText().toString();
                bundle1.putInt("Id", id);
                bundle1.putString("CateName", cateName);
                bundle1.putString("Background", cateBG);
                bundle1.putString("Icon", cateIcon);
                intent1.putExtras(bundle1);
                setResult(202, intent1);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}