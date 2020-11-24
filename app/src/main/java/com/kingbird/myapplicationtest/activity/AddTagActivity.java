package com.kingbird.myapplicationtest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.kingbird.myapplicationtest.R;
import com.kingbird.myapplicationtest.adapter.LabelTypeAdapter;
import com.kingbird.myapplicationtest.bean.LabelTypeBean;
import com.kingbird.myapplicationtest.dialog.InputDialog;
import com.kingbird.myapplicationtest.view.FlowTagLayout;
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
 * 标签选择
 *
 * @author panyingdao
 * @date 2020/11/24.
 */
public class AddTagActivity extends AppCompatActivity {

    @BindView(R.id.titlebar)
    TitleBar mTitleBar;
    @BindView(R.id.et_color)
    EditText mInputColor;
    @BindView(R.id.et_brand)
    EditText mInputBrand;
    @BindView(R.id.flowTagLayout)
    FlowTagLayout mFlowTagLayout;
    @BindView(R.id.rv_label_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_title_color)
    TextView mTagTitle;

    private LabelTypeAdapter adapter;
    //标签集合
    private List<LabelTypeBean> tagList = new ArrayList<>();

    ArrayList<String> label = new ArrayList<>();
    public static int RESULT_CODE_GET_TAG = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_tag);
        ButterKnife.bind(this);

        initLabel();
        initTagColor();
        initLabelType();

        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {

            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {
                String etColor = mInputColor.getText().toString().trim();
                String etBrand = mInputBrand.getText().toString().trim();
                String dataReturn = etColor + " " + etBrand;
                KLog.e("最终标签为：" + etColor + " " + etBrand);
                Intent intent = new Intent();
                intent.putExtra("data_return", dataReturn);
                setResult(RESULT_CODE_GET_TAG, intent);
                finish();
            }
        });

        mInputColor.setOnClickListener(v -> {
            KLog.e("点击颜色输入框");
            mTagTitle.setText("颜色");
            tagList.clear();
            initTagColor();
            adapter.notifyDataSetChanged();
//            mInputColor.setFocusableInTouchMode(true);
//            mInputColor.requestFocus();
//            mInputColor.requestFocusFromTouch();
//            hideKeyBoard(mInputColor,true);
//            showInput(this, mInputColor);
            inputDialog(mInputColor, "颜色");
        });

        mInputBrand.setOnClickListener(v -> {
            KLog.e("点击品牌输入框");
            mTagTitle.setText("品牌+混合");
            tagList.clear();
            initTagBrand();
            adapter.notifyDataSetChanged();
//            mInputBrand.setFocusableInTouchMode(true);
//            mInputBrand.requestFocus();
//            mInputBrand.requestFocusFromTouch();
//            hideKeyBoard(mInputBrand,true);
//            showInput(this, mInputBrand);
            inputDialog(mInputBrand, "补充介绍");
        });
    }

    /**
     * 输入框弹窗
     *
     * @param textView 对象
     */
    private void inputDialog(TextView textView, String text) {
        new InputDialog.Builder(this)
                .setTitle(text)
                .setListener((dialog, content) -> {
                    textView.setText(content);
                })
                .show();
    }

    /**
     * 颜色标签初始化
     */
    private void initTagColor() {
        LabelTypeBean black = new LabelTypeBean(R.drawable.bg_black_round, "黑色");
        tagList.add(black);
        LabelTypeBean white = new LabelTypeBean(R.drawable.bg_white_round, "白色");
        tagList.add(white);
        LabelTypeBean gray = new LabelTypeBean(R.drawable.bg_gray_round, "灰色");
        tagList.add(gray);
        LabelTypeBean red = new LabelTypeBean(R.drawable.bg_red_round, "红色");
        tagList.add(red);
        LabelTypeBean orange = new LabelTypeBean(R.drawable.bg_orange_round, "橙色");
        tagList.add(orange);
        LabelTypeBean yellow = new LabelTypeBean(R.drawable.bg_yellow_round, "黄色");
        tagList.add(yellow);
        LabelTypeBean green = new LabelTypeBean(R.drawable.bg_green_round, "绿色");
        tagList.add(green);
        LabelTypeBean blue = new LabelTypeBean(R.drawable.bg_blue_round, "蓝色");
        tagList.add(blue);
        LabelTypeBean purple = new LabelTypeBean(R.drawable.bg_purple_round, "紫色");
        tagList.add(purple);
        LabelTypeBean pink = new LabelTypeBean(R.drawable.bg_pink_round, "粉色");
        tagList.add(pink);
    }

    /**
     * 品牌标签初始化
     */
    private void initTagBrand() {
        LabelTypeBean nike = new LabelTypeBean(0, "NIKE");
        tagList.add(nike);
        LabelTypeBean adss = new LabelTypeBean(0, "阿迪达斯");
        tagList.add(adss);
        LabelTypeBean yierk = new LabelTypeBean(0, "意尔康");
        tagList.add(yierk);
        LabelTypeBean tebu = new LabelTypeBean(0, "特步");
        tagList.add(tebu);
        LabelTypeBean nike2 = new LabelTypeBean(0, "NIKE");
        tagList.add(nike2);
        LabelTypeBean adss2 = new LabelTypeBean(0, "阿迪达斯");
        tagList.add(adss2);
        LabelTypeBean yierk2 = new LabelTypeBean(0, "意尔康");
        tagList.add(yierk2);
        LabelTypeBean tebu2 = new LabelTypeBean(0, "特步");
        tagList.add(tebu2);
    }

    /**
     * 标签类别初始化
     */
    private void initLabelType() {
        adapter = new LabelTypeAdapter(tagList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setNestedScrollingEnabled(true);
        //下拉刷新
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        //上拉加载
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        //为下拉刷新添加事件
        mRefreshLayout.setOnRefreshListener(refreshlayout -> {
//            initData();
            //传入false表示刷新失败
            refreshlayout.finishRefresh(true/*,false*/);
        });
        //为上拉下载添加事件
        mRefreshLayout.setOnLoadMoreListener(refreshlayout -> {
//            initData();
            //传入false表示加载失败
            refreshlayout.finishLoadMore(2000/*,false*/);
        });
        adapter.setOnItemClickListener((position, data) -> {
            KLog.e("点击下标：" + position);
            LabelTypeBean labelTypeBean = new LabelTypeBean(data.get(position).getImageId(), data.get(position).getTag());
            int id = labelTypeBean.getImageId();
            String tag = labelTypeBean.getTag();
            KLog.e("数据：" + id);
            KLog.e("数据：" + tag);
            if (id > 0) {
                mInputColor.setText(tag);
            } else {
                mInputBrand.setText(tag);
            }

//            TransactionBean transactionBean = new TransactionBean(data.get(position).getTransactionType(),
//                    data.get(position).getTransactionTypeTime(), data.get(position).getTransactionSum());
//            Intent intent = new Intent(MyWalletActivity.this, TransactionDetailsActivity.class);
//            intent.putExtra("transactionBean", transactionBean);
//            startActivity(intent);
        });
    }

    /**
     * 标签显示列表初始化
     */
    private void initLabel() {
        label.add("NIKE");
        label.add("灰色");
        label.add("联名款");
        mFlowTagLayout.addTags(label);
    }

    /**
     * 键盘控制
     */
    private void hideKeyBoard(View view, boolean b) {
        if (b) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);//关闭输入法
        } else if (!b) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                    .showSoftInput(view, 0);
        }

    }

    /**
     * 调出键盘
     * view为可见状态的EditText
     **/
    public static void showInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }
    }


}