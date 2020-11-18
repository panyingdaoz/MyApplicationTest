package com.kingbird.myapplicationtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：失败 * withdraw_sucess
 * @author :Pan Yingdao
 * @date : on 2020/11/17 0017 15:39:04
 */
public class WithdrawRecordAdapter extends RecyclerView.Adapter<WithdrawRecordAdapter.ListViewHolder> {

    private List<WithdrawRecordBean> data;

    private Context context;

    private AdapterView.OnItemClickListener onItemClickListener;

    public WithdrawRecordAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public WithdrawRecordAdapter(List<WithdrawRecordBean> data) {
        this.data = data;
    }

    public void setData(List<WithdrawRecordBean> data) {
        this.data = data;
// 刷新数据
        notifyDataSetChanged();
    }

    //为相应逻辑添加数据
    public void addData(List<WithdrawRecordBean> entities) {
        if (data == null) {
            data = new ArrayList<>();
        }
        KLog.e("添加数据");
        this.data.addAll(entities);
        notifyDataSetChanged();
    }

    /**
     * 创建一个viewHolder并返回
     *
     * @param parent   相当于Item存放的容器
     * @param viewType 指定布局文件的类型
     * @return 返回viewHolder对象
     */
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //指定对应的布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.withdraw_record_item, parent, false);
        return new ListViewHolder(view);

    }

    /**
     * 将数据加载到布局文件的控件上
     *
     * @param holder   需要绑定数据的viewHolder
     * @param position 列表数据的下标
     */
    @Override
    public void onBindViewHolder(@NonNull WithdrawRecordAdapter.ListViewHolder holder, final int position) {
        WithdrawRecordBean entity = data.get(position);
//        holder.transactionType.setText(entity.getMsg());
        //将绑定的布局设置点击事件
        KLog.e("加载数据：" + entity.getCardName());
        holder.cardName.setText(entity.getCardName());
        holder.money.setText(entity.getMoney());
        holder.odd.setText(entity.getWithdrawOdd());
        holder.result.setText(entity.getResult());

    }

    /**
     * 返回数据的大小
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    //为了系统复用控件
    static class ListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_card)
        TextView cardName;
        @BindView(R.id.tv_money)
        TextView money;
        @BindView(R.id.tv_odd)
        TextView odd;
        @BindView(R.id.result)
        TextView result;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


