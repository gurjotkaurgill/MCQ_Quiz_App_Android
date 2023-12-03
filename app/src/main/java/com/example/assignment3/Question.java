package com.example.assignment3;

public class Question {
    private String text;
    private boolean answer;
    private int color;

    public Question(String text, boolean answer, int color) {
        this.text = text;
        this.answer = answer;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
