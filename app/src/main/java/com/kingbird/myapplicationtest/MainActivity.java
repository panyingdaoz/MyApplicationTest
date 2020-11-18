package com.kingbird.myapplicationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity {

    private List<String> tags = new ArrayList<>();
    List<int[]> colors = new ArrayList<int[]>();
    int[] color1 = {getResources().getColor(R.color.label1),getResources().getColor(R.color.label2),
            getResources().getColor(R.color.label3)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_issue_card_dialog);

        tags.add("猫奴");
        tags.add("安静");
        tags.add("努力");
        tags.add("添加标签");
        colors.add(color1);

        TagContainerLayout mTagContainerLayout = findViewById(R.id.layout_label);

//        for (int i=0;i<tags.size();i++){
//            //设置标签字的颜色
////            mTagContainerLayout.setTagTextColor(getResources().getColor(R.color.white));
//            if (i==1){
//                //设置单个item背景颜色
//                mTagContainerLayout.setTagBackgroundColor(Color.parseColor("#76C8FF"));
//                KLog.e("设置 i 位置的背景色");
//            }else if (i==2){
////                mTagContainerLayout.setTagTextColor(getResources().getColor(R.color.white));
//                mTagContainerLayout.setTagBackgroundColor(Color.parseColor("#FF74B2"));
//                KLog.e("设置 i 位置的背景色");
//            }else if (i==3){
//                KLog.e("设置 i 位置的背景色");
////                mTagContainerLayout.setTagTextColor(getResources().getColor(R.color.white));
////                mTagContainerLayout.setTagBackgroundColor(Color.parseColor("#FF7B74"));
//            }
//            mTagContainerLayout.addTag(tags.get(i));
//        }
        mTagContainerLayout.setTags(tags);
    }
}