package com.example.btl_android.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.itemView.IncomeItemView;
import com.example.btl_android.objectClass.Transaction;
import com.example.btl_android.recycle.Recycle_Income;

import java.util.ArrayList;
import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeItemView>{

    private List<Transaction> transactionList;
    private List<String> key;

    public IncomeAdapter(){}


    public IncomeAdapter(List<Transaction> transactionList, List<String> key) {
        this.transactionList = transactionList;
        this.key = key;
    }

    @NonNull
    @Override
    public IncomeItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IncomeItemView(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeItemView holder, int position) {
        holder.bind(transactionList.get(position), key.get(position));

    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
    public List<Transaction> getIncome(){ return transactionList;}
    public List<String> getKey(){return key;}

    public void setDataList(ArrayList<Transaction> dataList, ArrayList<String> khoa){
        transactionList = dataList;
        key = khoa;
        notifyDataSetChanged();
    }
}
