package com.example.assignment3;

import android.app.Application;

import java.util.ArrayList;

public class AppLevelVars extends Application {

    private int currentQuestionIndex, noOfCorrectAnswers,noOfQuestions,percentProgress;
    ArrayList<Question> questions = new ArrayList<>(10);
    ArrayList<Question> userAddedQuestions = new ArrayList<>(0);
    FileManager fileManager = new FileManager();

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }

    public int getNoOfCorrectAnswers() {
        return noOfCorrectAnswers;
    }

    public void setNoOfCorrectAnswers(int noOfCorrectAnswers) {
        this.noOfCorrectAnswers = noOfCorrectAnswers;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(int noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public ArrayList<Question> getUserAddedQuestions() {
        return userAddedQuestions;
    }

    public void setUserAddedQuestions(ArrayList<Question> userAddedQuestions) {
        this.userAddedQuestions = userAddedQuestions;
    }

    public int getPercentProgress() {
        return percentProgress;
    }

    public void setPercentProgress(int percentProgress) {
        this.percentProgress = percentProgress;
    }
}
