package com.example.btl_android;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CategoryAdapter extends BaseAdapter {

    private ArrayList<Catergory> data;
    private Activity context;
    private LayoutInflater inflater;

    public CategoryAdapter(ArrayList<Catergory> data, Activity act) {
        this.data = data;
        this.context = act;
        this.inflater = (LayoutInflater)act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if(v == null){
            v = inflater.inflate(R.layout.category_item, null);
        }
        ImageView imgBack = v.findViewById(R.id.imgBackground);
        ImageView imgIcon = v.findViewById(R.id.imgIcon);
        TextView tvCategory = v.findViewById(R.id.txtCategoryName);
        tvCategory.setText(data.get(i).getCatergoryName());
        return v;
    }

}
