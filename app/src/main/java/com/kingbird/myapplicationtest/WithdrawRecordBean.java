package com.kingbird.myapplicationtest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 说明：
 *
 * @author :Pan Yingdao
 * @date : on 2020/11/17 0017 15:17:11
 */
public class WithdrawRecordBean implements Parcelable {

    private String cardName;
    private String withdrawOdd;
    private String money;
    private String result;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getWithdrawOdd() {
        return withdrawOdd;
    }

    public void setWithdrawOdd(String withdrawOdd) {
        this.withdrawOdd = withdrawOdd;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static Creator<WithdrawRecordBean> getCREATOR() {
        return CREATOR;
    }

    public WithdrawRecordBean(String cardName, String withdrawOdd, String money, String result) {
        this.cardName = cardName;
        this.withdrawOdd = withdrawOdd;
        this.money = money;
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.cardName);
        dest.writeString(this.money);
        dest.writeString(this.withdrawOdd);
        dest.writeString(this.result);
    }

    protected WithdrawRecordBean(Parcel in) {
        this.cardName = in.readString();
        this.money = in.readString();
        this.withdrawOdd = in.readString();
        this.result = in.readString();
    }

    public static final Creator<WithdrawRecordBean> CREATOR = new Creator<WithdrawRecordBean>() {
        @Override
        public WithdrawRecordBean createFromParcel(Parcel source) {
            return new WithdrawRecordBean(source);
        }

        @Override
        public WithdrawRecordBean[] newArray(int size) {
            return new WithdrawRecordBean[size];
        }
    };
}
