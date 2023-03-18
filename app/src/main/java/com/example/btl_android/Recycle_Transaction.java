package com.example.btl_android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Recycle_Transaction {
    private Context mcontext;
    private Recycle_Transaction.TransactionAdapter transAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Transaction> transactions, List<String> keys){
        mcontext = context;
        transAdapter = new Recycle_Transaction.TransactionAdapter(transactions, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(transAdapter);
        transAdapter.notifyDataSetChanged();
    }

    class TransactionItemView extends RecyclerView.ViewHolder{
        private TextView tCategory;
        private TextView tAccount;
        private TextView tMoney;
        private TextView tDate;

        private String key;

        public TransactionItemView(ViewGroup parent) {
            super(LayoutInflater.from(mcontext).inflate(R.layout.transaction_item, parent, false));
            tCategory = (TextView) itemView.findViewById(R.id.txtCategory);
            tAccount = (TextView) itemView.findViewById(R.id.txtAccount);
            tMoney = (TextView) itemView.findViewById(R.id.txtMoney);
            tDate = (TextView) itemView.findViewById(R.id.txtDate);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mcontext, TransactionActivity.class);
                    intent.putExtra("key", key);
                    //intent.putExtra("id", tId.getText().toString());
                    intent.putExtra("name", tName.getText().toString());
                    intent.putExtra("money", tMoney.getText().toString());

                    mcontext.startActivity(intent);
                }
            });*/
        }
        public void bind(Transaction transaction, String key){
            this.key = key;
            tCategory.setText(transaction.getCategory());
            tAccount.setText(transaction.getAccount());
            tMoney.setText(transaction.getMoney());
            tDate.setText(transaction.getDate());
        }

    }

    class TransactionAdapter extends RecyclerView.Adapter<TransactionItemView>{

        private List<Transaction> transactionList;
        private List<String> key;

        public TransactionAdapter(List<Transaction> transactionList, List<String> key) {
            this.transactionList = transactionList;
            this.key = key;
        }

        @NonNull
        @Override
        public TransactionItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TransactionItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TransactionItemView holder, int position) {
            holder.bind(transactionList.get(position), key.get(position));
        }

        @Override
        public int getItemCount() {
            return transactionList.size();
        }
    }
}
