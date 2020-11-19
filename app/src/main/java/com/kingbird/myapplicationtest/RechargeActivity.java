package com.kingbird.myapplicationtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.kingbird.myapplicationtest.dialog.SweetAlertDialog;
import com.socks.library.KLog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  充值界面
 *
 * @author pan
 */
public class RechargeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.recharge)
    Button mRecharge;
    @BindView(R.id.titlebar)
    TitleBar mTitleBar;
    @BindView(R.id.cb_alipay)
    AppCompatCheckBox mAlipay;
    @BindView(R.id.cb_wechat)
    AppCompatCheckBox mWechat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);

        mRecharge.setOnClickListener(this);
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
        if (v.getId() == R.id.recharge) {
            KLog.e("开始充值");
            if (mAlipay.isChecked() && mWechat.isChecked()) {
                KLog.e("只能同时选择一种充值方式");
            }
            KLog.e("支付宝选中结果：" + mAlipay.isChecked());
            KLog.e("微信选中结果：" + mWechat.isChecked());
        }
    }
}