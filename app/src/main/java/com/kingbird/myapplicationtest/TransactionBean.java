package com.kingbird.myapplicationtest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 说明：
 *
 * @author :Pan Yingdao
 * @date : on 2020/11/17 0017 15:17:11
 */
public class TransactionBean implements Parcelable {

    private String transactionType;
    private String transactionTypeTime;
    private String transactionSum;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionTypeTime() {
        return transactionTypeTime;
    }

    public void setTransactionTypeTime(String transactionTypeTime) {
        this.transactionTypeTime = transactionTypeTime;
    }

    public String getTransactionSum() {
        return transactionSum;
    }

    public void setTransactionSum(String transactionSum) {
        this.transactionSum = transactionSum;
    }

    public TransactionBean(String transactionType, String transactionTypeTime, String transactionSum) {
        this.transactionType = transactionType;
        this.transactionTypeTime = transactionTypeTime;
        this.transactionSum = transactionSum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.transactionType);
        dest.writeString(this.transactionTypeTime);
        dest.writeString(this.transactionSum);
    }

    protected TransactionBean(Parcel in) {
        this.transactionType = in.readString();
        this.transactionTypeTime = in.readString();
        this.transactionSum = in.readString();
    }

    public static final Parcelable.Creator<TransactionBean> CREATOR = new Parcelable.Creator<TransactionBean>() {
        @Override
        public TransactionBean createFromParcel(Parcel source) {
            return new TransactionBean(source);
        }

        @Override
        public TransactionBean[] newArray(int size) {
            return new TransactionBean[size];
        }
    };
}
