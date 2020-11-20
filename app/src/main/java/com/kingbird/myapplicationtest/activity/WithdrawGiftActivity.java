package com.kingbird.myapplicationtest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.kingbird.myapplicationtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Administrator
 */
public class WithdrawGiftActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_alipay)
    ImageView mAlipay;
    @BindView(R.id.titlebar)
    TitleBar mTitleBar;

    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_withdraw_gift);
        ButterKnife.bind(this);

        mAlipay.setOnClickListener(this);

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
            case R.id.tv_alipay:
                if (flag) {
                    flag = false;
                    if (mAlipay.getVisibility()==View.GONE){
                        mAlipay.setVisibility(View.VISIBLE);
                    }
                    mAlipay.setImageDrawable(getResources().getDrawable(R.drawable.withdraw_sucess));
                } else {
                    if (mAlipay.getVisibility()==View.GONE){
                        mAlipay.setVisibility(View.VISIBLE);
                    }
                    flag = true;
                    mAlipay.setImageDrawable(getResources().getDrawable(R.drawable.withdraw_fail));
                }
                break;
            default:
        }
    }
}