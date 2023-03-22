package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_android.fragment.HomeFragment;
import com.google.gson.Gson;

public class CategoryListActivity extends AppCompatActivity {

    private TableLayout cate0, cate1, cate2, cate3, cate4, cate5, cate6, cate7, cate8, cate9, cate10, cate11, cate12, cate13;
    private TextView t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13;
    private ConstraintLayout backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        cate0 = findViewById(R.id.opt0);
        cate1 = findViewById(R.id.opt1);
        cate2 = findViewById(R.id.opt2);
        cate3 = findViewById(R.id.opt3);
        cate4 = findViewById(R.id.opt4);
        cate5 = findViewById(R.id.opt5);
        cate6 = findViewById(R.id.opt6);
        cate7 = findViewById(R.id.opt7);
        cate8 = findViewById(R.id.opt8);
        cate9 = findViewById(R.id.opt9);
        cate10 = findViewById(R.id.opt10);
        cate11 = findViewById(R.id.opt11);
        cate12 = findViewById(R.id.opt12);
        cate13 = findViewById(R.id.opt13);

        t0 = findViewById(R.id.tvOpt0);
        t1 = findViewById(R.id.tvOpt1);
        t2 = findViewById(R.id.tvOpt2);
        t3 = findViewById(R.id.tvOpt3);
        t4 = findViewById(R.id.tvOpt4);
        t5 = findViewById(R.id.tvOpt5);
        t6 = findViewById(R.id.tvOpt6);
        t7 = findViewById(R.id.tvOpt7);
        t8 = findViewById(R.id.tvOpt8);
        t9 = findViewById(R.id.tvOpt9);
        t10 = findViewById(R.id.tvOpt10);
        t11 = findViewById(R.id.tvOpt11);
        t12 = findViewById(R.id.tvOpt12);
        t13 = findViewById(R.id.tvOpt13);

        backBtn = findViewById(R.id.backLayout);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cate0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = true;
                HomeFragment.resourceId = R.drawable.coin;
                HomeFragment.text = t0.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });

        cate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = false;
                HomeFragment.resourceId = R.drawable.fuel;
                HomeFragment.text = t1.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });

        cate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = false;
                HomeFragment.resourceId = R.drawable.lightning;
                HomeFragment.text = t2.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });

        cate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = false;
                HomeFragment.resourceId = R.drawable.water;
                HomeFragment.text = t3.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });

        cate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = false;
                HomeFragment.resourceId = R.drawable.shopping_bag;
                HomeFragment.text = t4.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });

        cate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = false;
                HomeFragment.resourceId = R.drawable.car;
                HomeFragment.text = t5.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });

        cate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = false;
                HomeFragment.resourceId = R.drawable.fork;
                HomeFragment.text = t6.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });

        cate7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = false;
                HomeFragment.resourceId = R.drawable.gift;
                HomeFragment.text = t7.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });

        cate8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = false;
                HomeFragment.resourceId = R.drawable.grocery;
                HomeFragment.text = t8.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });

        cate9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = false;
                HomeFragment.resourceId = R.drawable.healthcare;
                HomeFragment.text = t9.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });

        cate10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = false;
                HomeFragment.resourceId = R.drawable.holiday;
                HomeFragment.text = t10.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });

        cate11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = false;
                HomeFragment.resourceId = R.drawable.internet;
                HomeFragment.text = t11.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });

        cate12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = false;
                HomeFragment.resourceId = R.drawable.school;
                HomeFragment.text = t12.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });

        cate13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.check = false;
                HomeFragment.resourceId = R.drawable.sports;
                HomeFragment.text = t13.getText().toString();
                Intent intent = new Intent(CategoryListActivity.this, AccListActivity.class);
                startActivity(intent);
            }
        });
    }
}