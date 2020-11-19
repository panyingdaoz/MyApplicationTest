package com.kingbird.myapplicationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.socks.library.KLog;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;

import java.util.Date;

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

    private TimePickerView mDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);
        ButterKnife.bind(this);

        mHometown.setOnClickListener(v -> {

        });
        mBirthday.setOnClickListener(v -> {
            KLog.e("点击生日");
            showDatePicker();
        });
    }

    private void showDatePicker() {
        if (mDatePicker == null) {
            mDatePicker = new TimePickerBuilder(this, (date, v) -> {
                KLog.e("日期：" + date);
//                XToastUtils.toast(DateUtils.date2String(date, DateUtils.yyyyMMdd.get())))

            }).setTimeSelectChangeListener(date ->
                    KLog.e("pvTime", "onTimeSelectChanged"))
                    .build();
        }
        mDatePicker.show();
    }
}