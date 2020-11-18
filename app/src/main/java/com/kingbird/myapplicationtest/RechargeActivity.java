package com.kingbird.myapplicationtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kingbird.myapplicationtest.dialog.SweetAlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Administrator
 */
public class RechargeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.recharge)
    Button mRecharge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);

        mRecharge.setOnClickListener(this);
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