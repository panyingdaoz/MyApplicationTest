package com.kingbird.myapplicationtest.wallet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.kingbird.myapplicationtest.R;
import com.kingbird.myapplicationtest.RechargeActivity;
import com.kingbird.myapplicationtest.RecyclerViewListDemoAdapter;
import com.kingbird.myapplicationtest.TransactionBean;
import com.kingbird.myapplicationtest.TransactionDetailsActivity;
import com.kingbird.myapplicationtest.WithdrawActivity;
import com.kingbird.myapplicationtest.WithdrawGiftActivity;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的钱包 activity
 *
 * @author Administrator
 */
public class MyWalletActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_transaction_record)
    RecyclerView mRecyclerView;
    @BindView(R.id.wallet)
    ImageView mWallet;
    @BindView(R.id.gift)
    ImageView mGift;
    @BindView(R.id.bank_card)
    ImageView mBankCard;
    @BindView(R.id.credit_card)
    ImageView mCreditCard;
    @BindView(R.id.tv_recharge)
    TextView mRecharge;
    @BindView(R.id.tv_withdraw)
    TextView mWithdraw;
    @BindView(R.id.titlebar)
    TitleBar mTitleBar;
    private int moneyState = 0;

    private RecyclerViewListDemoAdapter mAdapter;

    /**
     *  要设置的数据
     */
    private List<TransactionBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);
//        mRefreshLayout=findViewById(R.id.refreshLayout);
//        mRecyclerView=findViewById(R.id.rv_transaction_record);

        recyclerRefresh();
        initData();
    }

    //适配数据
    private void recyclerRefresh() {
        mGift.setOnClickListener(this);
        mWallet.setOnClickListener(this);
        mBankCard.setOnClickListener(this);
        mCreditCard.setOnClickListener(this);
        mWithdraw.setOnClickListener(this);
        mRecharge.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerViewListDemoAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        //下拉刷新
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        //上拉加载
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        //为下拉刷新添加事件
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
            initData();
            //传入false表示刷新失败
            refreshlayout.finishRefresh(true/*,false*/);
        });
        //为上拉下载添加事件
        mRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
            initData();
            //传入false表示加载失败
            refreshlayout.finishLoadMore(2000/*,false*/);
        });
        mAdapter.setOnItemClickListener((position, data) -> {
            KLog.e("点击下标：" + position);
            TransactionBean transactionBean = new TransactionBean(data.get(position).getTransactionType(),
                    data.get(position).getTransactionTypeTime(), data.get(position).getTransactionSum());

            Intent intent = new Intent(MyWalletActivity.this, TransactionDetailsActivity.class);
            intent.putExtra("transactionBean", transactionBean);
            startActivity(intent);
        });

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

    private void initData() {
        KLog.e("初始化数据");
        data = new ArrayList<>();
        TransactionBean entity = new TransactionBean(
                "充值", "019-9-1  17:00", "+10.00");
        data.add(entity);

        TransactionBean entity2 = new TransactionBean(
                "提现", "019-9-1  17:00", "-5.00");
        data.add(entity2);

        TransactionBean entity3 = new TransactionBean(
                "活动名称", "019-9-1  17:00", "-5.00");
        data.add(entity3);

        TransactionBean entity4 = new TransactionBean(
                "活动名称", "019-9-1  17:00", "-5.00");
        data.add(entity4);

        TransactionBean entity5 = new TransactionBean(
                "活动名称", "019-9-1  17:00", "+10.00");
        data.add(entity5);

        mAdapter.addData(data);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gift:
                moneyState = 1;
                mGift.setImageDrawable(getResources().getDrawable(R.mipmap.gift_selector));
                mWallet.setImageDrawable(getResources().getDrawable(R.mipmap.wallet));
                mBankCard.setImageDrawable(getResources().getDrawable(R.mipmap.bank_card));
                mCreditCard.setImageDrawable(getResources().getDrawable(R.mipmap.credit_card));
                mRecharge.setVisibility(View.GONE);
                break;
            case R.id.wallet:
                moneyState = 0;
                mRecharge.setVisibility(View.VISIBLE);
                mGift.setImageDrawable(getResources().getDrawable(R.mipmap.gift));
                mWallet.setImageDrawable(getResources().getDrawable(R.mipmap.wallet_selector));
                mBankCard.setImageDrawable(getResources().getDrawable(R.mipmap.bank_card));
                mCreditCard.setImageDrawable(getResources().getDrawable(R.mipmap.credit_card));
                break;
            case R.id.bank_card:
                mGift.setImageDrawable(getResources().getDrawable(R.mipmap.gift));
                mWallet.setImageDrawable(getResources().getDrawable(R.mipmap.wallet));
                mBankCard.setImageDrawable(getResources().getDrawable(R.mipmap.bank_card));
                mCreditCard.setImageDrawable(getResources().getDrawable(R.mipmap.credit_card));
                break;
            case R.id.credit_card:
                mGift.setImageDrawable(getResources().getDrawable(R.mipmap.gift));
                mWallet.setImageDrawable(getResources().getDrawable(R.mipmap.wallet));
                mBankCard.setImageDrawable(getResources().getDrawable(R.mipmap.bank_card));
                mCreditCard.setImageDrawable(getResources().getDrawable(R.mipmap.credit_card));
                break;
            case R.id.tv_withdraw:
                Intent intent;
                if (moneyState == 1) {
                    intent = new Intent(MyWalletActivity.this, WithdrawGiftActivity.class);
                } else {
                    intent = new Intent(MyWalletActivity.this, WithdrawActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.tv_recharge:
                Intent intent1 = new Intent(MyWalletActivity.this, RechargeActivity.class);
                startActivity(intent1);
                break;
            default:
        }
    }

}