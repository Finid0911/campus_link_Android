package com.example.btl_android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity {

    private ListView cateList;
    private ImageView backImg, addCateImg;
    public static ArrayList<Catergory> CategoryList;
    private CategoryAdapter cateAdapter;
    int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        cateList = findViewById(R.id.lstCategory);
        backImg = findViewById(R.id.backBtn);
        addCateImg = findViewById(R.id.addCateIV);

        CategoryList = new ArrayList<>();
        CategoryList.add(new Catergory(1, "Shopping", "background01", "icon01"));
        CategoryList.add(new Catergory(2, "Food", "background02", "icon02"));

        cateAdapter = new CategoryAdapter(CategoryList, this);
        cateList.setAdapter(cateAdapter);
        registerForContextMenu(cateList);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addCateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(CategoryListActivity.this, CategoryDetailActivity.class);
                startActivityForResult(intentAdd, 201);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Bundle b = data.getExtras();
            int id = b.getInt("Id");
            String name = b.getString("CateName");
            String background = b.getString("Background");
            String icon = b.getString("Icon");
            Catergory c = new Catergory(10, name, background, icon);
            if(requestCode == 201 && resultCode == 202){
                CategoryList.add(c);
            }
            cateList.setAdapter(cateAdapter);
            cateAdapter.notifyDataSetChanged();
        }
    }
}