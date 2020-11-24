package com.kingbird.myapplicationtest.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.kingbird.myapplicationtest.R;
import com.kingbird.myapplicationtest.bean.TagInfoBean;
import com.kingbird.myapplicationtest.utils.PxtoSpUtils;
import com.kingbird.myapplicationtest.view.FlowTagLayout;
import com.kingbird.myapplicationtest.view.TagImageView;
import com.kingbird.myapplicationtest.view.TagTextView;
import com.kingbird.myapplicationtest.widget.ViewTooltip;
import com.socks.library.KLog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * class
 *
 * @author panyingdao
 * @date 2020/11/22.
 */
public class PersonalDataActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.flow_tops)
    FlowTagLayout mTops;
    @BindView(R.id.flow_bottoms)
    FlowTagLayout mBottoms;
    @BindView(R.id.flow_shoes)
    FlowTagLayout mShoes;
    @BindView(R.id.flow_hair)
    FlowTagLayout mHair;
    @BindView(R.id.flow_other)
    FlowTagLayout mFlowOther;
    @BindView(R.id.user_manual)
    TextView mUsreManual;
    @BindView(R.id.title)
    TitleBar mTitleBar;
    @BindView(R.id.iv_info1)
    ImageView mIvInfol1;
    @BindView(R.id.iv_info2)
    ImageView mIvInfol2;
    @BindView(R.id.image_card)
    ImageView mImageCard;
    @BindView(R.id.tag_content)
    TagImageView tagImageView;

    private List<String> topsList = new ArrayList<>();
    private List<String> bottomList = new ArrayList<>();
    public static int RESULT_CODE_GET_TAG = 110;

    private int imageH;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.find_issue_personal_data_scroll);

        ButterKnife.bind(this);

        initView();
        initListener();
        initLabel();
        initUserManual();

        mTops.addTags(topsList);
        mBottoms.addTags(bottomList);

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
                Intent intent = new Intent(PersonalDataActivity.this, EditInformationActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * 控件添加监听
     */
    private void initListener() {
        mIvInfol1.setOnClickListener(this);
        mIvInfol2.setOnClickListener(this);
    }

    /**
     * 使用说明
     */
    private void initUserManual() {
        SpannableString str = new SpannableString(getResources().getText(R.string.user_manual));
        str.setSpan(new MyClickText(this), 20, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mUsreManual.setText(str);
        //不设置 没有点击事件
        mUsreManual.setMovementMethod(LinkMovementMethod.getInstance());
        //设置点击后的颜色为透明
        mUsreManual.setHighlightColor(Color.TRANSPARENT);
    }

    /**
     * 标签初始化
     */
    private void initLabel() {
//        PersonalLabelBean labelBean = new PersonalLabelBean("墨绿色打底线衣");
        topsList.add("墨绿色打底线衣");
//        PersonalLabelBean labelBeanB = new PersonalLabelBean("灰色毛呢大衣");
        topsList.add("灰色毛呢大衣");
        bottomList.add("黑色 nike 九分裤");
    }

    double rawX2,rawY2;

    private void initView() {
        tagImageView.setClickTagListener(bean ->
                Toast.makeText(PersonalDataActivity.this,
                        "标签被点击 == " + bean.getName(), Toast.LENGTH_SHORT).show());
        //添加标签
        tagImageView.setAddTagListener((path, rawX, rawY) -> {

            rawX2=rawY;
            rawY2=rawY;

            final TagInfoBean bean = new TagInfoBean();
            bean.setCanMove(true);
            bean.setNotesTagId(652);
            bean.setNotesTagType(TagTextView.TAG_TEXT);
            //通过手机中的图片地址  或者  网络拉取的图片信息  获得图片宽高
            bean.setPicWidth(1010);
            bean.setPicHeight(1324);
            bean.setUrl("tag点的链接url");
            // 显示控件的显示 依照图片的本身的宽高比例进行动态设置
            bean.setWidth(PxtoSpUtils.getScreenW(this));
            //标签在控件上的比例
            bean.setLeft(!(rawX > bean.getWidth() / 2));
            bean.setX(rawX / bean.getWidth());
            bean.setY(rawY / imageH);
            bean.setHeight(imageH);
//            ViewDialogFragment dialogFragment = new ViewDialogFragment();
//            dialogFragment.setCallback(tabName -> {
//                if (TextUtils.isEmpty(tabName)) {
//                    tabName = "女孩";
//                }
//                bean.setName(tabName);
//                Log.e("zz", "onClick: "+bean.getName() + "  " + imageH );
//
//
//                tagImageView.addTag(bean);
//            });
//            dialogFragment.show(getSupportFragmentManager());

            openTagActvty();
        });
        //删除标签
        tagImageView.setDeleteTagListener((path, bean) ->
                Toast.makeText(PersonalDataActivity.this,
                        "删除标签 == " + bean.getName(), Toast.LENGTH_SHORT).show());
        //设置图片的路径
        //可用来标记这些标签属于哪张图片
        tagImageView.setPath("一般是本地图片地址,这里用的是资源图片");

        //添加初始标签
        List<TagInfoBean> tagInfoBeanList = new ArrayList<>();

        tagInfoBeanList.add(createTag1());
//        tagInfoBeanList.add(createTag2());
//        tagInfoBeanList.add(createTag3());

        //设置 图片的高度  可根据实际的图片高度比例  设置
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, imageH);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, imageH);
        findViewById(R.id.image).setLayoutParams(params);

        tagImageView.setLayoutParams(params);
        if (tagInfoBeanList.size() > 0) {
            tagImageView.setTagList(tagInfoBeanList);
        }
    }

    @NonNull
    private TagInfoBean createTag1() {
        TagInfoBean bean = new TagInfoBean();
        //该标签是否可以移动
        bean.setCanMove(false);
        bean.setLeft(false);
//        bean.setName("一杯奶茶");
        bean.setNotesTagId(652);
        bean.setNotesTagType(TagTextView.TAG_BRAND);

        //通过手机中的图片地址  或者  网络拉取的图片信息  获得图片宽高
        bean.setPicWidth(1010);
        bean.setPicHeight(1324);
        bean.setUrl("tag点的链接url");
        // 显示控件的显示 依照图片的本身的宽高比例进行动态设置
        bean.setWidth(PxtoSpUtils.getScreenW(this));
        imageH = 0;
        //项目中需求是只有1：1  和  3：4的比例   这个可根据实际修改   直接按图片比例也可以
        if (bean.getPicWidth() / bean.getPicHeight() > 0.85f) {
            imageH = (int) bean.getWidth();
        } else {
            imageH = (int) (bean.getWidth() * 4 / 3);
        }

        //标签原点在照片上的比例
        bean.setX(0.7513889);
        bean.setY(0.5864583);
        bean.setHeight(imageH);
        return bean;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_info1:
                ViewTooltip
                        .on(mIvInfol1)
                        .color(R.color.bg_info)
                        .autoHide(true, 3000)
                        .padding(20, 8, 12, 15)
                        .position(ViewTooltip.Position.TOP)
                        .text("经过本人认证的照片头像将享受任何功能优先推送")
                        .show();
                break;
            case R.id.iv_info2:
                ViewTooltip
                        .on(mIvInfol2)
                        .color(R.color.bg_info)
                        .autoHide(true, 3000)
                        .padding(20, 8, 12, 15)
                        .position(ViewTooltip.Position.TOP)
                        .text("全身照头像，非全身照不给予通过")
                        .show();
                break;
            default:
        }

    }

    /**
     * 打开标签activity
     */
    private void openTagActvty() {
        Intent intent = new Intent(PersonalDataActivity.this, AddTagActivity.class);
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CODE_GET_TAG) {
            if (data != null) {
                String dataReturn = data.getStringExtra("data_return");
                KLog.e("接到返回标签信息: " + dataReturn);

                final TagInfoBean bean = new TagInfoBean();
                bean.setCanMove(true);
                bean.setNotesTagId(652);
                bean.setNotesTagType(TagTextView.TAG_TEXT);
                //通过手机中的图片地址  或者  网络拉取的图片信息  获得图片宽高
                bean.setPicWidth(1010);
                bean.setPicHeight(1324);
                bean.setUrl("tag点的链接url");
                // 显示控件的显示 依照图片的本身的宽高比例进行动态设置
                bean.setWidth(PxtoSpUtils.getScreenW(this));
                //标签在控件上的比例
                bean.setLeft(!(rawX2 > bean.getWidth() / 2));
                bean.setX(rawX2 / bean.getWidth());
                bean.setY(rawY2 / imageH);
                bean.setHeight(imageH);
                bean.setName(dataReturn);
                tagImageView.addTag(bean);
            }
        }
    }

    /**
     * 文字点击
     */
    class MyClickText extends ClickableSpan {
        private Context context;

        public MyClickText(Context context) {
            this.context = context;
        }

        @Override
        public void updateDrawState(@NotNull TextPaint ds) {
            super.updateDrawState(ds);         //设置文本的颜色
            ds.setColor(getResources().getColor(R.color.click_text));
            //超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
            ds.setUnderlineText(false);
        }

        @Override
        public void onClick(@NotNull View widget) {
//            showCancleHint();//点击事件
            Toast.makeText(context, "触发点击事件!", Toast.LENGTH_SHORT).show();
        }
    }
}
