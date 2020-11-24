package com.kingbird.myapplicationtest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author Administrator
 */
public class ViewDialogFragment extends DialogFragment {

    public interface Callback {
        void onClick(String tabName);
    }

    private Callback callback;

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, "ViewDialogFragment");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.dialog_add_tag, null);
        final EditText tab_name = (EditText) view.findViewById(R.id.tab_name);
        builder.setView(view)
                .setPositiveButton("确定", (dialog, which) -> {
                    if (callback != null) {
                        callback.onClick(tab_name.getText().toString());
                    }
                })
        ;
        return builder.create();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        callback = null;
    }
}