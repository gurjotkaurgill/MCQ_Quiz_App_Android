package com.example.assignment3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AverageAlert extends DialogFragment {

    static String msg="";

    public static AverageAlert newInstance(String s) {
        msg = s;
        return new AverageAlert();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage(msg)
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> dismiss())
                .create();
    }
}
