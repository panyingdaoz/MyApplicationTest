package com.kingbird.myapplicationtest.wallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingbird.myapplicationtest.R;
import com.kingbird.myapplicationtest.RecyclerViewListDemoAdapter;
import com.kingbird.myapplicationtest.TransactionAdapter;
import com.kingbird.myapplicationtest.TransactionBean;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.socks.library.KLog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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

    //    private TransactionAdapter mAdapter;
    private RecyclerViewListDemoAdapter mAdapter;

    //要设置的数据
    private List<TransactionBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);
//        mRefreshLayout=findViewById(R.id.refreshLayout);
//        mRecyclerView=findViewById(R.id.rv_transaction_record);

        recyclerRefresh();

    }

    //适配数据
    private void recyclerRefresh() {
//        mAdapter = new TransactionAdapter(this, data);
        mGift.setOnClickListener(this);
        mWallet.setOnClickListener(this);
        mBankCard.setOnClickListener(this);
        mCreditCard.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerViewListDemoAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        //下拉刷新
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        //上拉加载
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        //为下拉刷新添加事件
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NotNull RefreshLayout refreshlayout) {
                initData();
                refreshlayout.finishRefresh(true/*,false*/);//传入false表示刷新失败
            }
        });
        //为上拉下载添加事件
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NotNull RefreshLayout refreshlayout) {
                initData();
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
        initData();

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gift:
                mGift.setImageDrawable(getResources().getDrawable(R.mipmap.gift_selector));
                mWallet.setImageDrawable(getResources().getDrawable(R.mipmap.wallet));
                mBankCard.setImageDrawable(getResources().getDrawable(R.mipmap.bank_card));
                mCreditCard.setImageDrawable(getResources().getDrawable(R.mipmap.credit_card));
                mRecharge.setVisibility(View.GONE);
                break;
            case R.id.wallet:
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
            default:
        }
    }
}