package com.example.assignment3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuestionFragment extends Fragment {

    static String question;
    static int color;
    public static QuestionFragment newInstance(String ques, int col){
        QuestionFragment qf = new QuestionFragment();
        question = ques;
        color = col;
        return qf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout,container,false);
        TextView questionTextView = v.findViewById(R.id.questionTextView);
        questionTextView.setText(question);
        v.setBackgroundColor(color);
        return v;
    }
}
