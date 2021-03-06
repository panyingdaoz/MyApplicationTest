package com.kingbird.myapplicationtest.adapter;

import com.kingbird.myapplicationtest.bean.LabelTypeBean;

import java.util.List;

/**
 * 说明：Recyc item监听类
 *
 * @author :Pan Yingdao
 * @date : on 2020/11/19 0019 16:02:40
 */
public interface OnTagItemClickListener {

    //参数（父组件，当前单击的View,单击的View的位置，数据）
    void onItemClick(int position, List<LabelTypeBean> data);
//    void onItemClick(RecyclerView parent, View view, int position, Map data);
    // void onItemLongClick(View view);类似，我这里没用就不写了
}
