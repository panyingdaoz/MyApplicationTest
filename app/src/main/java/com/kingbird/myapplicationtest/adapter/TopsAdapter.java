package com.kingbird.myapplicationtest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingbird.myapplicationtest.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 上装 adapter
 *
 * @author panyingdao
 * @date 2020/11/15.
 */
public class TopsAdapter extends RecyclerView.Adapter<TopsAdapter.ViewHolder> {

    private List<PersonalLabelBean> mTopsList;

    public TopsAdapter(List<PersonalLabelBean> topsList) {
        mTopsList = topsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_issue_label_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonalLabelBean labelBean = mTopsList.get(position);
        holder.textView.setText(labelBean.getText());

    }

    @Override
    public int getItemCount() {
        return mTopsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.label_name);
        }
    }
}
