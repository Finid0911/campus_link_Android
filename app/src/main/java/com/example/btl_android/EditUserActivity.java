package com.example.btl_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_android.objectClass.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class EditUserActivity extends AppCompatActivity {

    private EditText edtName, edtPhone;
    private TextView txtDate;
    private Spinner spGender;
    private Button saveBtn, cancelBtn;
    String[] gender = {"Male", "Female", "Other"};
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference(uid).child("user");
    private String gt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        edtName = findViewById(R.id.edtUsername);
        edtPhone = findViewById(R.id.edtPhone);
        txtDate = findViewById(R.id.txtDOB);
        spGender = findViewById(R.id.spinnerGender);
        saveBtn = findViewById(R.id.btnSave);
        cancelBtn = findViewById(R.id.btnCancel);

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gender);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(ad);

        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    DataSnapshot hm = task.getResult();
                    String name = String.valueOf(hm.child("name").getValue(String.class));
                    String dob = String.valueOf(hm.child("birth").getValue(String.class));
                    String sex = String.valueOf(hm.child("gender").getValue(String.class));
                    String phoneNumber = String.valueOf(hm.child("phone").getValue(String.class));
                    User user = new User();
                    user.setName(name);
                    user.setBirth(dob);
                    user.setGender(sex);
                    user.setPhone(phoneNumber);
                    edtName.setText(user.getName());
                    edtPhone.setText(user.getPhone());
                    txtDate.setText(user.getBirth());
                    if(user.getGender().equals("Male")){
                        spGender.setSelection(0);
                        System.out.println("Male");
                    }
                    else if(user.getGender().equals("Female")){
                        spGender.setSelection(1);
                        System.out.println("Female");
                    }
                    else if(user.getGender().equals("Others")){
                        spGender.getItemAtPosition(2);
                    }
                }
                else {
                    System.out.println("ko co kq");
                }
            }
        });


        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gt = spGender.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditUserActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Lấy ngày được chọn và hiển thị nó trên TextView
                                String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                                txtDate.setText(selectedDate);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference(uid).child("user");
                databaseReference.child("name").setValue(edtName.getText().toString());
                databaseReference.child("birth").setValue(txtDate.getText().toString());
                databaseReference.child("gender").setValue(gt);
                databaseReference.child("phone").setValue(edtPhone.getText().toString());
                Toast.makeText(EditUserActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}