package com.example.btl_android.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.btl_android.R;
import com.example.btl_android.adapter.IncomeAdapter;
import com.example.btl_android.adapter.TransactionAdapter;
import com.example.btl_android.objectClass.Transaction;
import com.example.btl_android.firebaseHelper.FirebaseHelper_Transaction;
import com.example.btl_android.recycle.Recycle_Income;
import com.example.btl_android.recycle.Recycle_Transaction;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class HistoryFragment extends Fragment {

    private IncomeAdapter incomeAdapter = new IncomeAdapter();
    private TransactionAdapter transactionAdapter = new TransactionAdapter();
    private EditText edtSearch;
    private ImageView popup;
    private TextView incomeTxt, expenseTxt;
    private RecyclerView rv;
    public boolean checked = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        edtSearch = (EditText) view.findViewById(R.id.edtSearch);
        popup = (ImageView) view.findViewById(R.id.listPopUp);
        rv = (RecyclerView) view.findViewById(R.id.rvTransactionHistory);
        incomeTxt = (TextView) view.findViewById(R.id.inTxt);
        expenseTxt = (TextView) view.findViewById(R.id.exTxt);
        expenseTxt.setBackgroundColor(Color.RED);
        expenseTxt.setTextColor(Color.WHITE);

        new FirebaseHelper_Transaction().readData(new FirebaseHelper_Transaction.DataStatus() {

            @Override
            public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {
                new Recycle_Transaction().setConfig(rv, getActivity(), transactions, keys);
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

        // Popup menu
        PopupMenu popupMenu = new PopupMenu(getActivity(), popup);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.mnuSort1){
                    /*Collections.sort(Recycle_Transaction.expense, new Comparator<Transaction>() {
                        @Override
                        public int compare(Transaction o1, Transaction o2) {
                            return Integer.compare(Integer.parseInt(o1.getMoney()), Integer.parseInt(o2.getMoney()));
                        }
                    });
                    ArrayList<Transaction> search = new ArrayList<>();
                    ArrayList<String> searchKey = new ArrayList<>();
                    for(String k : Recycle_Transaction.ks){
                        searchKey.add(k);
                    }
                    for(Transaction ts : Recycle_Transaction.expense){

                            search.add(ts);


                    }
                    transactionAdapter.setDataList1(search);
                    //transactionAdapter.notifyDataSetChanged();
                    rv.setAdapter(transactionAdapter);*/
                }

                else if(id == R.id.mnuSort2){

                }
                return false;
            }
        });

        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checked == false){
                    searchExpense(s.toString());
                }
                if(checked == true){
                    searchIncome(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        incomeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incomeTxt.setBackgroundColor(Color.GREEN);
                incomeTxt.setTextColor(Color.WHITE);
                expenseTxt.setBackgroundColor(Color.WHITE);
                expenseTxt.setTextColor(Color.BLACK);
                checked = true;
                new FirebaseHelper_Transaction().readData2(new FirebaseHelper_Transaction.DataStatus() {

                    @Override
                    public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {
                        new Recycle_Income().setConfig(rv, getActivity(), transactions, keys);
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
            }
        });

        expenseTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseTxt.setBackgroundColor(Color.RED);
                expenseTxt.setTextColor(Color.WHITE);
                incomeTxt.setBackgroundColor(Color.WHITE);
                incomeTxt.setTextColor(Color.BLACK);
                checked = false;
                new FirebaseHelper_Transaction().readData(new FirebaseHelper_Transaction.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {
                        new Recycle_Transaction().setConfig(rv, getActivity(), transactions, keys);
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
            }
        });

        return view;
    }

    public void searchIncome(String text){
        ArrayList<Transaction> search = new ArrayList<>();
        ArrayList<String> searchKey = new ArrayList<>();
        for(String k : Recycle_Income.ks){
            searchKey.add(k);
        }
        for(Transaction ts : Recycle_Income.income){
            if(ts.getAccount().toLowerCase().contains(text.toLowerCase())){
                search.add(ts);
            }
        }
        incomeAdapter.setDataList(search, searchKey);
        incomeAdapter.notifyDataSetChanged();
        rv.setAdapter(incomeAdapter);
    }

    public void searchExpense(String text){
        ArrayList<Transaction> search = new ArrayList<>();
        ArrayList<String> searchKey = new ArrayList<>();
        for(String k : Recycle_Transaction.ks){
            searchKey.add(k);
        }
        for(Transaction ts : Recycle_Transaction.expense){
            if(ts.getCategory().toLowerCase().contains(text.toLowerCase())){
                search.add(ts);
            }
            else if(ts.getMoney().contains(text.toString())){
                search.add(ts);
            }
        }
        transactionAdapter.setDataList(search, searchKey);
        transactionAdapter.notifyDataSetChanged();
        rv.setAdapter(transactionAdapter);
    }
    public int ss(int a, int b){
        if (a>b){
            return 1;
        }
        else{
            return 0;
        }
    }
}