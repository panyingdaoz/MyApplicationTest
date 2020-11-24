package com.kingbird.myapplicationtest.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.kingbird.myapplicationtest.R;
import com.kingbird.myapplicationtest.dialog.InputDialog;
import com.kingbird.myapplicationtest.view.FlowTagLayout;
import com.socks.library.KLog;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Administrator
 */
public class EditInformationActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_hometown)
    TextView mHometown;
    @BindView(R.id.tv_birthday)
    TextView mBirthday;
    @BindView(R.id.tv_nickname)
    TextView mNickname;
    @BindView(R.id.tv_gender)
    TextView mGender;
    @BindView(R.id.tv_emotion)
    TextView mEmotion;
    @BindView(R.id.tv_profession)
    TextView mProfession;
    @BindView(R.id.tv_school)
    TextView mSchool;
    @BindView(R.id.tv_hobby)
    TextView mHobby;
    @BindView(R.id.tv_ideal)
    TextView mIdeal;
    @BindView(R.id.tv_signature)
    TextView mSignature;
    @BindView(R.id.titlebar)
    TitleBar mTitleBar;
    @BindView(R.id.flowTagLayout)
    FlowTagLayout mFlowTagLayout;

    private TimePickerView pvCustomLunar;
    private OptionsPickerView pvOptions;
    private MyOkHttp mMyOkhttp;

    String url = "http://guolin.tech/api/china";
    private List<String> options1Items = new ArrayList<>();
    ArrayList<String> label = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_information);
        ButterKnife.bind(this);

        mMyOkhttp = new MyOkHttp();
        initClickListener();
        initLunarPicker();
        initOptionPicker();
        queryProvinces();
        initFlowTag();

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

    /**
     * 添加控件监听
     */
    private void initClickListener() {
        mNickname.setOnClickListener(this);
        mGender.setOnClickListener(this);
        mEmotion.setOnClickListener(this);
        mHobby.setOnClickListener(this);
        mIdeal.setOnClickListener(this);
        mProfession.setOnClickListener(this);
        mSchool.setOnClickListener(this);
        mSignature.setOnClickListener(this);
        mHometown.setOnClickListener(this);
        mBirthday.setOnClickListener(this);
    }

    /**
     * 标签初始化
     */
    private void initFlowTag() {
        label.add("猫奴");
        mFlowTagLayout.setItemBackGround(R.drawable.bg_label);
        mFlowTagLayout.addTags(label);
        mFlowTagLayout.setItemBackGround(R.drawable.bg_label2);
        mFlowTagLayout.addTag("安静");
        mFlowTagLayout.setItemBackGround(R.drawable.bg_label3);
        mFlowTagLayout.addTag("努力");
        mFlowTagLayout.setItemTextColor(1);
        mFlowTagLayout.setItemBackGround(R.drawable.bg_add_label);
        mFlowTagLayout.addTag("添加标签");

        mFlowTagLayout.setTagClickListener(position -> {
            KLog.e("当前点击的标签：" + mFlowTagLayout.getTagText(position));
            if (getString(R.string.add_label).equals(mFlowTagLayout.getTagText(position))) {
                new InputDialog.Builder(this)
                        .setTitle(getString(R.string.information_title))
                        .setListener((dialog, content) -> {
                            if (!content.isEmpty()) {
                                int tagSize = mFlowTagLayout.getTagSize();
                                KLog.e("当前标签大小：" + tagSize);
//                            mFlowTagLayout.setItemBackGround(R.drawable.bg_label);
                                if (tagSize > 0 && tagSize < 7) {
                                    int index = tagSize - 1;
//                                KLog.e("当前下标："+index);
                                    switch (index) {
                                        case 0:
                                            mFlowTagLayout.setItemBackGround(R.drawable.bg_label);
                                            break;
                                        case 1:
                                            mFlowTagLayout.setItemBackGround(R.drawable.bg_label2);
                                            break;
                                        case 2:
                                            mFlowTagLayout.setItemBackGround(R.drawable.bg_label3);
                                            break;
                                        case 3:
                                            mFlowTagLayout.setItemBackGround(R.drawable.bg_label4);
                                            break;
                                        case 4:
                                            mFlowTagLayout.setItemBackGround(R.drawable.bg_label5);
                                            break;
                                        case 5:
                                            mFlowTagLayout.setItemBackGround(R.drawable.bg_label6);
                                            break;
                                        default:
                                    }
                                    mFlowTagLayout.setItemTextColor(-1);
                                    mFlowTagLayout.addTagOfIndex(index, content);
                                } else {
                                    KLog.e("最多只能添加6个标签");
                                }
                            }
                        })
                        .show();
            } else {
                // getChildAt(position)方法在这很实用
                mFlowTagLayout.getChildAt(position).setSelected(!mFlowTagLayout.getChildAt(position).isSelected());
            }

        });
        mFlowTagLayout.setTagLongClickListener(position -> {
            KLog.e("触发长按监听");
            if (!getString(R.string.add_label).equals(mFlowTagLayout.getTagText(position))) {
                mFlowTagLayout.removeTagOfIndex(position);
            }
        });

    }

    /**
     * 条件选择器初始化
     */
    private void initOptionPicker() {
        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */
        pvOptions = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            KLog.e("返回选中的省份：" + options1Items.get(options1));
            mHometown.setText(options1Items.get(options1));
        })
//                .setTitleText("城市选择")
                //设置滚轮文字大小
                .setContentTextSize(20)
//                .setCancelColor(getResources().getColor(R.color.black80)
                //设置分割线的颜色
                .setDividerColor(Color.LTGRAY)
                //默认选中项
                .setSelectOptions(0, 1)
                .setBgColor(Color.WHITE)
                .setTitleBgColor(getResources().getColor(R.color.information))
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.RED)
                .setSubmitColor(getResources().getColor(R.color.submit))
                .setTextColorCenter(getResources().getColor(R.color.black80))
                //切换时是否还原，设置默认选中第一项。
                .isRestoreItem(true)
                //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isCenterLabel(false)
                .setLabels("省", "市", "区")
                //设置外部遮罩颜色
                .setOutSideColor(0x00000000)
                .setOptionsSelectChangeListener((options1, options2, options3) -> {
                    String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
//                    Toast.makeText(EditInformationActivity.this, str, Toast.LENGTH_SHORT).show();
                })
                .build();

//        pvOptions.setSelectOptions(1,1);
        //一级选择器
        pvOptions.setPicker(options1Items);
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
    }

    /**
     * 农历时间已扩展至 ： 1900 - 2100年
     */
    private void initLunarPicker() {
        //系统当前时间
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomLunar = new TimePickerBuilder(this, (date, v) -> {
            //选中事件回调
            Toast.makeText(EditInformationActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
            mBirthday.setText(getTime(date));
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_lunar, v -> {
                    TextView tvSubmit = v.findViewById(R.id.tv_finish);
                    TextView ivCancel = v.findViewById(R.id.iv_cancel);
                    tvSubmit.setOnClickListener(v1 -> {
                        pvCustomLunar.returnData();
                        pvCustomLunar.dismiss();
                    });
                    ivCancel.setOnClickListener(v12 -> pvCustomLunar.dismiss());
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isCenterLabel(false)
                .setDividerColor(Color.RED)
                .build();
    }

    /**
     * 生日格式转换
     *
     * @param date 日期
     * @return 结果
     */
    private String getTime(Date date) {
        //可根据需要自行截取数据显示
//        KLog.e("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return format.format(date);
    }

    /**
     * 根据传入的地址和类型从服务器上查询省市县数据
     */
    private void queryProvinces() {
        mMyOkhttp.get()
                .url(url)
                .tag(this)
                .enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
//                        KLog.e("doGet onSuccess:" + response);
                        try {
                            JSONArray allProvinces = new JSONArray(response);
                            for (int i = 0; i < allProvinces.length(); i++) {
                                JSONObject provinceObject = allProvinces.getJSONObject(i);
//                                KLog.e("获取到的城市："+provinceObject.getString("name"));
                                options1Items.add(provinceObject.getString("name"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        KLog.e("doGet onFailure:" + error_msg);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_birthday:
                pvCustomLunar.show();
                break;
            case R.id.tv_hometown:
                pvOptions.show();
                break;
            case R.id.tv_nickname:
                new InputDialog.Builder(this)
                        // 标题可以不用填写
                        .setTitle(getString(R.string.information_title))
//                        .setContent(mNickname.getText())
                        //.setHint(getString(R.string.personal_data_name_hint))
                        //.setConfirm("确定")
                        // 设置 null 表示不显示取消按钮
                        //.setCancel("取消")
                        // 设置点击按钮后不关闭对话框
                        //.setAutoDismiss(false)
                        .setListener((dialog, content) -> {
//                            if (!mNickname.getText().equals(content)) {
//                                mNameView.setRightText(content);
//                            }
                            mNickname.setText(content);
                        })
                        .show();
                break;
            case R.id.tv_gender:
//                new InputDialog.Builder(this)
//                        // 标题可以不用填写
//                        .setTitle(getString(R.string.information_title))
//                        .setListener((dialog, content) -> {
//                            mGender.setText(content);
//                        })
//                        .show();
                inputDialog(mGender);
                break;
            case R.id.tv_emotion:
                inputDialog(mEmotion);
                break;
            case R.id.tv_profession:
                inputDialog(mProfession);
                break;
            case R.id.tv_school:
                inputDialog(mSchool);
                break;
            case R.id.tv_hobby:
                inputDialog(mHobby);
                break;
            case R.id.tv_ideal:
                inputDialog(mIdeal);
                break;
            case R.id.tv_signature:
                inputDialog(mSignature);
                break;
            default:
        }
    }

    /**
     * 输入框弹窗
     *
     * @param textView 对象
     */
    private void inputDialog(TextView textView) {
        new InputDialog.Builder(this)
                .setTitle(getString(R.string.information_title))
                .setListener((dialog, content) -> {
                    textView.setText(content);
                })
                .show();
    }
}