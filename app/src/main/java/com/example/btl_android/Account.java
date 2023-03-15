package com.example.btl_android;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Account implements Parcelable {

    private int id;
    private String account_name;
    private float money;

    public Account(int id, String account_name, float money) {
        this.id = id;
        this.account_name = account_name;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public float getMoney() {
        return money;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(account_name);
        dest.writeFloat(money);
    }
}
