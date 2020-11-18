package com.kingbird.myapplicationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.os.Bundle;
import android.view.View;

import com.kingbird.myapplicationtest.dialog.SweetAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Administrator
 */
public class WithdrawActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.cb_alipay)
    AppCompatCheckBox mAlipay;
    @BindView(R.id.cb_wechat)
    AppCompatCheckBox mWechat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ButterKnife.bind(this);

        mAlipay.setOnClickListener(this);
        mWechat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_alipay:
                new SweetAlertDialog(this)
                        .setTitleText("申请提现成功!")
                        .setContentText("具体进度可在提现明细查看")
//                        .setConfirmText("OK")
                        .show();
                break;
            case R.id.cb_wechat:
                new SweetAlertDialog(this)
                        .setTitleText("申请提现失败!")
                        .setContentText("具体进度可在提现明细查看")
                        .show();
                break;
            default:
        }
    }
}