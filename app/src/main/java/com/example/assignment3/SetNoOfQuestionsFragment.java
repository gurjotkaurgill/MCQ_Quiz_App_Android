package com.example.assignment3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SetNoOfQuestionsFragment extends DialogFragment {

    interface radioButtonClickListener {
        void radioButtonClicked(int x);
    }
    radioButtonClickListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.get_no_of_questions_layout,container,false);
        RadioGroup radioGroup = v.findViewById(R.id.radioGroup);

        Button ok = v.findViewById(R.id.ok_noOfQues);
        Button cancel = v.findViewById(R.id.cancel_noOfQues);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checked = radioGroup.getCheckedRadioButtonId();
                if(checked == R.id.rb5){
                    listener.radioButtonClicked(5);
                }
                else if(checked == R.id.rb8){
                    listener.radioButtonClicked(8);
                }
                else if(checked == R.id.rb10){
                    listener.radioButtonClicked(10);
                }
                else {
                    //nothing selected - do nothing
                }
                dismiss();
            }
        });
        cancel.setOnClickListener(v1 -> dismiss());
        return v;
    }
}
