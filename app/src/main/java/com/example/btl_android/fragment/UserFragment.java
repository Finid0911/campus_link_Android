package com.example.btl_android.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btl_android.EditUserActivity;
import com.example.btl_android.LoginActivity;
import com.example.btl_android.R;
import com.example.btl_android.objectClass.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserFragment extends Fragment {

    private ImageView logoutImg, editImg;
    private TextView userName, gender, birth, phone, namePresent;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference(uid).child("user");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        logoutImg = (ImageView) view.findViewById(R.id.logoutImg);
        editImg = (ImageView) view.findViewById(R.id.editImg);
        userName = (TextView) view.findViewById(R.id.edtUsername);
        gender = (TextView) view.findViewById(R.id.txtGender);
        birth = (TextView) view.findViewById(R.id.txtBirth);
        phone = (TextView) view.findViewById(R.id.txtPhone);
        namePresent = (TextView) view.findViewById(R.id.namePresent);

        databaseReference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    DataSnapshot hm = task.getResult();
                    String name = String.valueOf(hm.child("name").getValue(String.class));
                    String dob = String.valueOf(hm.child("birth").getValue(String.class));
                    String sex = String.valueOf(hm.child("gender").getValue(String.class));
                    String phoneNumber = String.valueOf(hm.child("phone").getValue(String.class));
                    User user1 = new User();
                    user1.setName(name);
                    user1.setBirth(dob);
                    user1.setGender(sex);
                    user1.setPhone(phoneNumber);
                    userName.setText(user1.getName());
                    birth.setText(user1.getBirth());
                    gender.setText(user1.getGender());
                    phone.setText(user1.getPhone());
                    namePresent.setText(user1.getName());
                }
                else {
                    System.out.println("ko co kq");
                }
            }
        });

        editImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditUserActivity.class);
                startActivity(intent);
            }
        });

        logoutImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}