package com.example.btl_android.recycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.R;
import com.example.btl_android.basicClass.Transaction;

import java.util.List;

public class Recycle_Income {
    private Context mcontext;
    private Recycle_Income.TransactionAdapter transAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Transaction> transactions, List<String> keys){
        mcontext = context;
        transAdapter = new Recycle_Income.TransactionAdapter(transactions, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(transAdapter);
        transAdapter.notifyDataSetChanged();
    }

    class TransactionItemView extends RecyclerView.ViewHolder{

        private String key;
        private TextView tCategory;
        private TextView tAccount;
        private TextView tMoney;
        private TextView tDate;
        private TextView tSyntax;

        public TransactionItemView(ViewGroup parent) {
            super(LayoutInflater.from(mcontext).inflate(R.layout.income_item, parent, false));
            tCategory = (TextView) itemView.findViewById(R.id.txtCategory);
            tAccount = (TextView) itemView.findViewById(R.id.txtAccount);
            tMoney = (TextView) itemView.findViewById(R.id.txtMoney);
            tDate = (TextView) itemView.findViewById(R.id.txtDate);
            tSyntax = (TextView) itemView.findViewById(R.id.txtSyntax);

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

    class TransactionAdapter extends RecyclerView.Adapter<Recycle_Income.TransactionItemView>{

        private List<Transaction> transactionList;
        private List<String> key;

        public TransactionAdapter(List<Transaction> transactionList, List<String> key) {
            this.transactionList = transactionList;
            this.key = key;
        }

        @NonNull
        @Override
        public Recycle_Income.TransactionItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Recycle_Income.TransactionItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull Recycle_Income.TransactionItemView holder, int position) {
            holder.bind(transactionList.get(position), key.get(position));

        }

        @Override
        public int getItemCount() {
            return transactionList.size();
        }
    }
}
