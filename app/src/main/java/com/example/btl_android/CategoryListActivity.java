package com.example.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity {

    private ListView cateList;
    private ImageView backImg;
    public static ArrayList<Catergory> CategoryList;
    private CategoryAdapter cateAdapter;
    int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        cateList = findViewById(R.id.lstCategory);
        backImg = findViewById(R.id.backBtn);

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
    }
}