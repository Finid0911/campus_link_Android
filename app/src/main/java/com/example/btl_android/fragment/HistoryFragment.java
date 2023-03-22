package com.example.btl_android.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl_android.R;
import com.example.btl_android.objectClass.Transaction;
import com.example.btl_android.firebaseHelper.FirebaseHelper_Transaction;
import com.example.btl_android.recycle.Recycle_Income;
import com.example.btl_android.recycle.Recycle_Transaction;

import java.util.List;

public class HistoryFragment extends Fragment {

    private EditText edtSearch;
    private ImageView popup;
    private TextView incomeTxt, expenseTxt;
    private RecyclerView rv;
    //private Recycle_Transaction.TransactionAdapter transactionAdapter;

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

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Recycle_Transaction.TransactionAdapter.getFilter().filter(s.toString());
                //transactionAdapter.getFilter().filter(s.toString());
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
}