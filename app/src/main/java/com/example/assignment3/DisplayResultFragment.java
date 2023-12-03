package com.example.assignment3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DisplayResultFragment extends DialogFragment {

    interface SaveResult {
        void saveResult(int no);
    }
    SaveResult listener;
    static String msg="";
    static int saveNo;

    public static DisplayResultFragment newInstance(String s, int no) {
        msg = s;
        saveNo = no;
        return new DisplayResultFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage(msg)
                .setPositiveButton(getString(R.string.save), (dialog, which) -> listener.saveResult(saveNo))
                .setNegativeButton(getString(R.string.ignore), (dialog, which) -> dismiss())
                .create();
    }

}
