package com.example.assignment3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Question implements Parcelable {
    private String text;
    private boolean answer;
    private int color;

    public Question(String text, boolean answer, int color) {
        this.text = text;
        this.answer = answer;
        this.color = color;
    }

    protected Question(Parcel in) {
        text = in.readString();
        answer = in.readByte() != 0;
        color = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getText() {
        return text;
    }


    public boolean isAnswer() {
        return answer;
    }

    public int getColor() {
        return color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeByte((byte) (answer ? 1 : 0));
        dest.writeInt(color);
    }

}
