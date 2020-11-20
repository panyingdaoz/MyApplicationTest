package com.kingbird.myapplicationtest.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.kingbird.myapplicationtest.R;
import com.kingbird.myapplicationtest.dialog.SweetAlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
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
    @BindView(R.id.titlebar)
    TitleBar mTitleBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_withdraw);
        ButterKnife.bind(this);

        mAlipay.setOnClickListener(this);
        mWechat.setOnClickListener(this);
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
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
            case R.id.tv_recharge:

                break;
            default:
        }
    }
}