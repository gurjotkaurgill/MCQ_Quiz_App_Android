package com.example.assignment3;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddQuestionFragment extends DialogFragment {

    interface AddNewQuestion {
        void addQuestion(String question, boolean answer);
    }

    AddNewQuestion listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.add_question_fragment,container,false);

        EditText questionText = v.findViewById(R.id.editText_add_ques);
        RadioGroup radioGroup = v.findViewById(R.id.radioGroup_addQues);
        RadioButton trueBtn = v.findViewById(R.id.true_addQues);
        RadioButton falseButton = v.findViewById(R.id.false_addQues);
        Button ok = v.findViewById(R.id.okBtn_addQues);
        Button cancel = v.findViewById(R.id.cancelBtn_addQues);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(questionText.getText())) {
                    questionText.setError(v.getContext().getString(R.string.quesRequired));
                }
                else if(!trueBtn.isChecked() && !falseButton.isChecked()) {
                    trueBtn.setError(v.getContext().getString(R.string.ansRequired));
                    falseButton.setError(v.getContext().getString(R.string.ansRequired));
                }
                else {
                    boolean ans = trueBtn.isChecked() ? true : false;
                    listener.addQuestion(questionText.getText().toString(),ans);
                    dismiss();
                }
            }
        });

        cancel.setOnClickListener(v1 -> dismiss());

        return v;
    }

}
