package com.example.btl_android.fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_android.AccListActivity;
import com.example.btl_android.CategoryListActivity;
import com.example.btl_android.MainActivity;
import com.example.btl_android.objectClass.Account;
import com.example.btl_android.AddAccountActivity;
import com.example.btl_android.firebaseHelper.FirebaseHelper;
import com.example.btl_android.firebaseHelper.FirebaseHelper_Transaction;
import com.example.btl_android.R;
import com.example.btl_android.objectClass.User;
import com.example.btl_android.recycle.Recycle_Account2;
import com.example.btl_android.recycle.Recycle_Income;
import com.example.btl_android.recycle.Recycle_Transaction;
import com.example.btl_android.objectClass.Transaction;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public ArrayList<Account> AccountList;
    private TextView showMore, income, outcome, userName;
    private RecyclerView lstAcc, lstTrans;
    private TableLayout cate1, cate2, cate3, cate4, cate5, cate6, cateMore, cateIncome;
    private TextView t1, t2, t3, t4, t5, t6, t7, t8;
    private ImageView addAcc;
    public static String text;
    public static boolean check = false;
    public static int resourceId;
    public static int checked = 0;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference(uid).child("user");

    private OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public HomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        addAcc = (ImageView) view.findViewById(R.id.delBtn);
        showMore = (TextView) view.findViewById(R.id.moreTV);
        lstAcc = (RecyclerView) view.findViewById(R.id.rvAccount);
        lstTrans = (RecyclerView) view.findViewById(R.id.rvTransaction);
        income = (TextView) view.findViewById(R.id.txtIn);
        outcome = (TextView) view.findViewById(R.id.txtOut);
        userName = (TextView) view.findViewById(R.id.usernameTV);
        outcome.setBackgroundColor(Color.RED);
        outcome.setTextColor(Color.WHITE);

        if(userName.getText() == null){
            userName.setText("User");
        }
        else{
            databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful() && task.getResult() != null) {
                        DataSnapshot hm = task.getResult();
                        String name = String.valueOf(hm.child("name").getValue(String.class));
                        User user1 = new User();
                        user1.setName(name);
                        userName.setText(user1.getName());
                    }
                    else {
                        System.out.println("ko co kq");
                    }
                }
            });
        }

        //category in main activity
        cate1 = (TableLayout) view.findViewById(R.id.opt1);
        cate2 = (TableLayout) view.findViewById(R.id.cate2);
        cate3 = (TableLayout) view.findViewById(R.id.cate3);
        cate4 = (TableLayout) view.findViewById(R.id.cate4);
        cate5 = (TableLayout) view.findViewById(R.id.opt3);
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
                new Recycle_Account2().setConfig(lstAcc, getActivity(), accounts, keys);
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

        /*for(ArrayList<Transaction> t: lstTrans){

        }*/

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
                if (mListener != null) {
                    mListener.onFragmentInteraction(1);
                }
            }
        });

        cate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                resourceId = R.drawable.shopping_bag;
                text = t1.getText().toString();
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                startActivity(intent);
            }
        });


        cate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                text = t2.getText().toString();
                resourceId = R.drawable.car;
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                startActivity(intent);
            }
        });

        cate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                text = t3.getText().toString();
                resourceId = R.drawable.fork;
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                startActivity(intent);
            }
        });

        cate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                resourceId = R.drawable.lightning;
                text = t4.getText().toString();
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                startActivity(intent);
            }
        });

        cate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                text = t5.getText().toString();
                resourceId = R.drawable.water;
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                startActivity(intent);
            }
        });

        cate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                text = t6.getText().toString();
                resourceId = R.drawable.fuel;
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                startActivity(intent);
            }
        });

        cateMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = false;
                text = t7.getText().toString();
                Intent intent = new Intent(getActivity(), CategoryListActivity.class);
                startActivity(intent);
            }
        });

        cateIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check = true;
                text = t8.getText().toString();
                resourceId = R.drawable.coin;
                Intent intent = new Intent(getActivity(), AccListActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}