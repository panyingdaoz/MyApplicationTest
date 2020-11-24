package com.kingbird.myapplicationtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kingbird.myapplicationtest.R;
import com.kingbird.myapplicationtest.bean.LabelTypeBean;
import com.socks.library.KLog;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 说明：标签 adapter
 *
 * @author Pan Yingdao
 * @time : 2020-11-24
 */
public class LabelTypeAdapter extends RecyclerView.Adapter<LabelTypeAdapter.ViewHolder> {

    private List<LabelTypeBean> data;
    private Context mContext;
    private OnTagItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnTagItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public LabelTypeAdapter(List<LabelTypeBean> objects, Context context) {
        data = objects;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //指定对应的布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_tag_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LabelTypeAdapter.ViewHolder holder, int position) {
        LabelTypeBean labelType = data.get(position);
        assert labelType != null;
        String orderNumber = labelType.getTag();
        KLog.e("每次更新的Item：" + orderNumber);
        holder.tvColor.setText(orderNumber);
        int id = labelType.getImageId();
        if (id > 0) {
            holder.ivColor.setVisibility(View.VISIBLE);
            Glide.with(mContext).asBitmap().load(id).into(holder.ivColor);
        } else {
            holder.ivColor.setVisibility(View.GONE);
        }
        holder.linearLayout.setOnClickListener(v ->
                onItemClickListener.onItemClick(position, data));

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_color)
        ImageView ivColor;
        @BindView(R.id.tv_color)
        TextView tvColor;
        @BindView(R.id.linear)
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
