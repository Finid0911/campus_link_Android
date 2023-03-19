package com.example.btl_android.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.btl_android.AccListActivity;
import com.example.btl_android.basicClass.Account;
import com.example.btl_android.AddAccountActivity;
import com.example.btl_android.firebaseHelper.FirebaseHelper;
import com.example.btl_android.firebaseHelper.FirebaseHelper_Transaction;
import com.example.btl_android.R;
import com.example.btl_android.recycle.Recycle2;
import com.example.btl_android.recycle.Recycle_Income;
import com.example.btl_android.recycle.Recycle_Transaction;
import com.example.btl_android.basicClass.Transaction;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public ArrayList<Account> AccountList;
    private TextView addAcc, showMore, income, outcome;
    private RecyclerView lstAcc, lstTrans;
    private TableLayout cate1, cate2, cate3, cate4, cate5, cate6, cateMore, cateIncome;
    private TextView t1, t2, t3, t4, t5, t6, t7, t8;
    public static String text;
    public static boolean check = false;
    

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        addAcc = (TextView) view.findViewById(R.id.delBtn);
        showMore = (TextView) view.findViewById(R.id.moreTV);
        lstAcc = (RecyclerView) view.findViewById(R.id.rvAccount);
        lstTrans = (RecyclerView) view.findViewById(R.id.rvTransaction);
        income = (TextView) view.findViewById(R.id.txtIn);
        outcome = (TextView) view.findViewById(R.id.txtOut);
        outcome.setBackgroundColor(Color.RED);
        outcome.setTextColor(Color.WHITE);

        //category in main activity
        cate1 = (TableLayout) view.findViewById(R.id.cate1);
        cate2 = (TableLayout) view.findViewById(R.id.cate2);
        cate3 = (TableLayout) view.findViewById(R.id.cate3);
        cate4 = (TableLayout) view.findViewById(R.id.cate4);
        cate5 = (TableLayout) view.findViewById(R.id.cate5);
        cate6 = (TableLayout) view.findViewById(R.id.cate6);
        cateMore = (TableLayout) view.findViewById(R.id.cateMore);
        cateIncome = (TableLayout) view.findViewById(R.id.income);

        // text of actegories
        t1 = (TextView) view.findViewById(R.id.shoppingTxt);
        t2 = (TextView) view.findViewById(R.id.vehicleTxt);
        t3 = (TextView) view.findViewById(R.id.foodTxt);
        t4 = (TextView) view.findViewById(R.id.electricTxt);
        t5 = (TextView) view.findViewById(R.id.waterTxt);
        t6 = (TextView) view.findViewById(R.id.fuelTxt);
        t7 = (TextView) view.findViewById(R.id.moreTV);
        t8 = (TextView) view.findViewById(R.id.incomeTxt);

        new FirebaseHelper().readData(new FirebaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Account> accounts, List<String> keys) {
                new Recycle2().setConfig(lstAcc, getActivity(), accounts, keys);
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

        new FirebaseHelper_Transaction().readData(new FirebaseHelper_Transaction.DataStatus() {

            @Override
            public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {
                new Recycle_Transaction().setConfig(lstTrans, getActivity(), transactions, keys);
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

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                income.setBackgroundColor(Color.GREEN);
                income.setTextColor(Color.WHITE);
                outcome.setBackgroundColor(Color.WHITE);
                outcome.setTextColor(Color.BLACK);
                new FirebaseHelper_Transaction().readData2(new FirebaseHelper_Transaction.DataStatus() {

                    @Override
                    public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {
                        new Recycle_Income().setConfig(lstTrans, getActivity(), transactions, keys);
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

        outcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outcome.setBackgroundColor(Color.RED);
                outcome.setTextColor(Color.WHITE);
                income.setBackgroundColor(Color.WHITE);
                income.setTextColor(Color.BLACK);
                new FirebaseHelper_Transaction().readData(new FirebaseHelper_Transaction.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {
                        new Recycle_Transaction().setConfig(lstTrans, getActivity(), transactions, keys);
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

        // add new account
        addAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddAccountActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryFragment historyFragment = new HistoryFragment();
            }
        });

        cate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                text = t1.getText().toString();
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                Gson gson = new Gson();
                String jsonAccount = gson.toJson(AccountList);
                intent.putExtra("list", jsonAccount);
                startActivityForResult(intent, 101);
            }
        });


        cate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                text = t2.getText().toString();
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                Gson gson = new Gson();
                String jsonAccount = gson.toJson(AccountList);
                intent.putExtra("list", jsonAccount);
                startActivityForResult(intent, 101);
            }
        });

        cate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                text = t3.getText().toString();
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                Gson gson = new Gson();
                String jsonAccount = gson.toJson(AccountList);
                intent.putExtra("list", jsonAccount);
                startActivityForResult(intent, 101);
            }
        });

        cate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                text = t4.getText().toString();
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                Gson gson = new Gson();
                String jsonAccount = gson.toJson(AccountList);
                intent.putExtra("list", jsonAccount);
                startActivityForResult(intent, 101);
            }
        });

        cate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                text = t5.getText().toString();
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                Gson gson = new Gson();
                String jsonAccount = gson.toJson(AccountList);
                intent.putExtra("list", jsonAccount);
                startActivityForResult(intent, 101);
            }
        });

        cate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                text = t6.getText().toString();
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                Gson gson = new Gson();
                String jsonAccount = gson.toJson(AccountList);
                intent.putExtra("list", jsonAccount);
                startActivityForResult(intent, 101);
            }
        });

        cateMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                text = t7.getText().toString();
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                Gson gson = new Gson();
                String jsonAccount = gson.toJson(AccountList);
                intent.putExtra("list", jsonAccount);
                startActivityForResult(intent, 101);
            }
        });

        cateIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = true;
                text = t8.getText().toString();
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                Gson gson = new Gson();
                String jsonAccount = gson.toJson(AccountList);
                intent.putExtra("list", jsonAccount);
                startActivityForResult(intent, 102);
            }
        });

        return view;
    }
}