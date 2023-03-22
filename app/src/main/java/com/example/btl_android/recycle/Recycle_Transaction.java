package com.example.btl_android.recycle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.R;
import com.example.btl_android.TransactionDetail;
import com.example.btl_android.objectClass.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Recycle_Transaction {
    private Context mcontext;
    public Recycle_Transaction.TransactionAdapter transAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Transaction> transactions, List<String> keys){
        mcontext = context;
        transAdapter = new Recycle_Transaction.TransactionAdapter(transactions, keys);
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
        private ImageView tImg;

        public TransactionItemView(ViewGroup parent) {
            super(LayoutInflater.from(mcontext).inflate(R.layout.transaction_item, parent, false));
            tCategory = (TextView) itemView.findViewById(R.id.txtCategory);
            tAccount = (TextView) itemView.findViewById(R.id.txtAccount);
            tMoney = (TextView) itemView.findViewById(R.id.txtMoney);
            tDate = (TextView) itemView.findViewById(R.id.txtDate);
            tImg = (ImageView) itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mcontext, TransactionDetail.class);
                    intent.putExtra("key", key);
                    //intent.putExtra("id", tId.getText().toString());
                    intent.putExtra("name", tAccount.getText().toString());
                    intent.putExtra("category", tCategory.getText().toString());
                    intent.putExtra("money", tMoney.getText().toString());
                    intent.putExtra("date", tDate.getText().toString());
                    intent.putExtra("imgId", tImg.getDrawable().toString());

                    mcontext.startActivity(intent);
                }
            });

        }
        public void bind(Transaction transaction, String key){
            this.key = key;
            tCategory.setText(transaction.getCategory());
            tAccount.setText(transaction.getAccount());
            tMoney.setText(transaction.getMoney() + " đ");
            tDate.setText(transaction.getDate());
            tImg.setImageResource(transaction.getImgId());
        }

    }

    class TransactionAdapter extends RecyclerView.Adapter<TransactionItemView> implements Filterable {

        public List<Transaction> transactionList;
        private List<Transaction> tempTransactionList;
        private List<String> key;

        public TransactionAdapter(List<Transaction> transactionList, List<String> key) {
            this.transactionList = transactionList;
            this.key = key;
            this.tempTransactionList = transactionList;
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

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String search = constraint.toString();
                    if(search.isEmpty()){
                        transactionList = tempTransactionList;
                    }
                    else{
                        List<Transaction> list = new ArrayList<>();
                        for(Transaction t : tempTransactionList){
                            if(t.getAccount().toLowerCase().contains(search.toLowerCase())){
                                list.add(t);
                            }
                        }
                        transactionList = list;
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = transactionList;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    transactionList = (List<Transaction>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
    }
}
