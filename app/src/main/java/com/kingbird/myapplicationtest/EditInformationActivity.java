package com.kingbird.myapplicationtest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.socks.library.KLog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Administrator
 */
public class EditInformationActivity extends AppCompatActivity {

    @BindView(R.id.tv_hometown)
    TextView mHometown;
    @BindView(R.id.tv_birthday)
    TextView mBirthday;

    private TimePickerView pvCustomLunar;
    private OptionsPickerView pvOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_information);
        ButterKnife.bind(this);

        initLunarPicker();
        mHometown.setOnClickListener(v -> {
            KLog.e("点击家乡");
            pvOptions.show();
        });
        mBirthday.setOnClickListener(v -> {
            KLog.e("点击生日");
            pvCustomLunar.show();
        });
    }

    /**
     *  条件选择器初始化
     */
    private void initOptionPicker() {

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()
//                        + options2Items.get(options1).get(options2)
                        /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
//                mHometown.setText(tx);
            }
        })
//                .setTitleText("城市选择")
                //设置滚轮文字大小
                .setContentTextSize(20)
                //设置分割线的颜色
                .setDividerColor(Color.LTGRAY)
                //默认选中项
                .setSelectOptions(0, 1)
                .setBgColor(Color.BLACK)
                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.RED)
                .setSubmitColor(getResources().getColor(R.color.submit))
                .setTextColorCenter(Color.LTGRAY)
                //切换时是否还原，设置默认选中第一项。
                .isRestoreItem(true)
                //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isCenterLabel(false)
                .setLabels("省", "市", "区")
                //设置外部遮罩颜色
                .setOutSideColor(0x00000000)
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
                        Toast.makeText(EditInformationActivity.this, str, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

//        pvOptions.setSelectOptions(1,1);
//        pvOptions.setPicker(options1Items);//一级选择器
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
    }

    /**
     * 农历时间已扩展至 ： 1900 - 2100年
     */
    private void initLunarPicker() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
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
                .setLayoutRes(R.layout.pickerview_custom_lunar, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        TextView tvSubmit = v.findViewById(R.id.tv_finish);
                        TextView ivCancel = v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(v1 -> {
                            pvCustomLunar.returnData();
                            pvCustomLunar.dismiss();
                        });
                        ivCancel.setOnClickListener(v12 -> pvCustomLunar.dismiss());
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();
    }

    /**
     *  生日格式转换
     * @param date 日期
     * @return 结果
     */
    private String getTime(Date date) {
        //可根据需要自行截取数据显示
//        KLog.e("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return format.format(date);
    }

}