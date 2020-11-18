package com.kingbird.myapplicationtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：
 *
 * @author :Pan Yingdao
 * @date : on 2020/11/17 0017 14:42:11
 */
public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private Context context;
    public List<TransactionBean> strList;
    private RefreshInterface refreshInterface;

    public TransactionAdapter(Context context, List<TransactionBean> strList) {
        this.context = context;
        this.strList = strList;
//        this.refreshInterface = refreshInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_my_wallet_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        viewHolder.group.setOnClickListener(v -> refreshInterface.Refresh(i));
        viewHolder.transactionType.setText(strList.get(i).toString());
        viewHolder.transactionTypeTime.setText(strList.get(i).toString());
        viewHolder.transactionSum.setText(strList.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return strList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.transaction_type)
         TextView transactionType;
        @BindView(R.id.transaction_type_time)
         TextView transactionTypeTime;
        @BindView(R.id.transaction_sum)
         TextView transactionSum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //加载数据
    public void loadMore(List<TransactionBean> strings) {
        strList.addAll(strings);
        notifyDataSetChanged();
    }

    //刷新数据
    public void refreshData(List<TransactionBean> strings) {
        strList.addAll(0, strings);
        notifyDataSetChanged();
    }

    //点击的回掉，将点击的下标返回
    public interface RefreshInterface {
        void refresh(int ints);
    }
}

