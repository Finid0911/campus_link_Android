package com.example.btl_android;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.btl_android.fragment.ViewPagerAdapter;
import com.example.btl_android.objectClass.Account;
import com.example.btl_android.objectClass.Transaction;
import com.example.btl_android.firebaseHelper.FirebaseHelper;
import com.example.btl_android.firebaseHelper.FirebaseHelper_Transaction;
import com.example.btl_android.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {

    public TextView account, category, txtTime;
    private EditText moneyInput;
    private String key, name, cate, money;
    private int imgId;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btnConirm;
    private ConstraintLayout backBtn, transactionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        account = findViewById(R.id.txtAcc);
        category = findViewById(R.id.txtCate);
        txtTime = findViewById(R.id.txtTime);
        moneyInput = findViewById(R.id.edtMoneyInput);
        btnConirm = findViewById(R.id.btnConfirm);
        backBtn = findViewById(R.id.backLayout);
        transactionLayout = findViewById(R.id.transactionLayout);

        key = getIntent().getStringExtra("key");
        //id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        money = getIntent().getStringExtra("money");

        account.setText(String.valueOf(name));
        category.setText(HomeFragment.text);
        cate = HomeFragment.text;
        imgId = HomeFragment.resourceId;

        // option button
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);

        // Get calender real time
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        // Hiển thị ngày hiện tại trên một TextView
        String currentDate = String.format("%02d/%02d/%d", day, month, year);
        txtTime.setText(currentDate);
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ngày hiện tại của hệ thống
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(TransactionActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Lấy ngày được chọn và hiển thị nó trên TextView
                                String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                                txtTime.setText(selectedDate);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
                System.out.println(txtTime.getText().toString());
            }
        });

        // Sự kiện quay lại layout trước
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Sự kiện nhấn nút xác nhận giao dịch
        btnConirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moneyInput.getText().toString().length() == 0){
                    Toast.makeText(TransactionActivity.this, "Không đươc để trống", Toast.LENGTH_LONG).show();
                }
                else{
                    Long mm = Long.parseLong(moneyInput.getText().toString());
                    Transaction transaction = new Transaction();
                    //transaction.setId(EditId.getText().toString());
                    transaction.setAccount(name);
                    transaction.setCategory(cate);
                    transaction.setMoney(String.valueOf(mm));
                    transaction.setDate(txtTime.getText().toString());
                    transaction.setImgId(imgId);
                    new FirebaseHelper_Transaction().addData(transaction, new FirebaseHelper_Transaction.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<Transaction> transactions, List<String> keys) {

                        }

                        @Override
                        public void DataIsInsert() {
                            Toast.makeText(TransactionActivity.this, "Add successfully", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void DataIsUpdate() {

                        }

                        @Override
                        public void DataIsDeleted() {

                        }
                    });

                    Account ac = new Account();
                    Long m = Long.parseLong(money) - mm;
                    if(m < 0){
                        Toast.makeText(TransactionActivity.this, "Not enough money to carry out the transaction", Toast.LENGTH_LONG).show();
                    }
                    else{
                        ac.setAccount_name(name);
                        ac.setMoney(String.valueOf(m));
                    }

                    new FirebaseHelper().editData(key, ac, new FirebaseHelper.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<Account> accounts, List<String> keys) {

                        }

                        @Override
                        public void DataIsInsert() {

                        }

                        @Override
                        public void DataIsUpdate() {
                            Toast.makeText(TransactionActivity.this, "Update successfully", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void DataIsDeleted() {

                        }
                    });
                    moneyInput = null;
                    finish();
                    Intent intent = new Intent(TransactionActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        moneyInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String mInput = moneyInput.getText().toString();
                btn1.setText(mInput + "");
                btn2.setText(mInput + "0");
                btn3.setText(mInput + "00");
                btn4.setText(mInput + "000");
                btn5.setText(mInput + "0000");
                btn6.setText(mInput + "00000");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyInput.setText(btn1.getText());
                moneyInput.requestFocus();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyInput.setText(btn2.getText());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyInput.setText(btn3.getText());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyInput.setText(btn4.getText());
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyInput.setText(btn5.getText());
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyInput.setText(btn6.getText());
            }
        });
    }

}