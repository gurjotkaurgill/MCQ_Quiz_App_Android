package com.example.assignment3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ResetAlert extends DialogFragment {

    interface DeleteResultsInterface{
        void deleteResults();
    }

    DeleteResultsInterface listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage(R.string.sureDelete)
                .setPositiveButton(getString(R.string.ok), (dialog, which) -> listener.deleteResults())
                .setNegativeButton(getString(R.string.cancel), (dialog, which) -> dismiss())
                .create();
    }
}
