package com.example.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        SetNoOfQuestionsFragment.radioButtonClickListener, ResetAlert.DeleteResultsInterface,
        DisplayResultFragment.SaveResult, AddQuestionFragment.AddNewQuestion {

    ArrayList<Question> shuffledQuestionsArrayList, userAddedQuestionsList;
    QuestionBank questionBank;
    FrameLayout frameLayout;
    Button trueButton, falseButton;
    ProgressBar progressBar;
    int currentQuestionIndex, noOfCorrectAnswers;
    int noOfQuestionsToDisplay, percentProgress;
    FileManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionBank = new QuestionBank();

        frameLayout = findViewById(R.id.frameLayout);
        trueButton = findViewById(R.id.trueButton);
        falseButton = findViewById(R.id.falseButton);
        progressBar = findViewById(R.id.progressBar);

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);

        fm = ((AppLevelVars)getApplication()).fileManager;

        if(getSupportFragmentManager().findFragmentById(R.id.frameLayout) != null){
            shuffledQuestionsArrayList = ((AppLevelVars)getApplication()).getQuestions();
            currentQuestionIndex = ((AppLevelVars) getApplication()).getCurrentQuestionIndex();
            noOfCorrectAnswers = ((AppLevelVars) getApplication()).getNoOfCorrectAnswers();
            noOfQuestionsToDisplay = ((AppLevelVars) getApplication()).getNoOfQuestions();
            percentProgress = ((AppLevelVars) getApplication()).getPercentProgress();
            userAddedQuestionsList = ((AppLevelVars)getApplication()).getUserAddedQuestions();
            Toast.makeText(this, "size: " + shuffledQuestionsArrayList.size(), Toast.LENGTH_SHORT).show();
        }
        else {
            shuffledQuestionsArrayList = questionBank.getQuestions(MainActivity.this);
            userAddedQuestionsList = new ArrayList<>(0);
            Collections.shuffle(shuffledQuestionsArrayList);
            currentQuestionIndex = 0;
            noOfCorrectAnswers = 0;
            percentProgress = 0;
            noOfQuestionsToDisplay = shuffledQuestionsArrayList.size();
            ((AppLevelVars)getApplication()).setQuestions(shuffledQuestionsArrayList);
            ((AppLevelVars) getApplication()).setCurrentQuestionIndex(currentQuestionIndex);
            ((AppLevelVars) getApplication()).setNoOfCorrectAnswers(noOfCorrectAnswers);
            ((AppLevelVars) getApplication()).setNoOfQuestions(noOfQuestionsToDisplay);
            ((AppLevelVars) getApplication()).setPercentProgress(percentProgress);
            ((AppLevelVars)getApplication()).setUserAddedQuestions(userAddedQuestionsList);
        }

        QuestionFragment fragment = QuestionFragment.newInstance(
                shuffledQuestionsArrayList.get(currentQuestionIndex).getText(),
                shuffledQuestionsArrayList.get(currentQuestionIndex).getColor());
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,fragment).commit();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.trueButton){
            //check if true - add to file - toast
            if(shuffledQuestionsArrayList.get(currentQuestionIndex).isAnswer()) {
                Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
                noOfCorrectAnswers++;
            }
            else {
                Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            //v.getId() == R.id.falseButton
            //check if false - toast
            if(!shuffledQuestionsArrayList.get(currentQuestionIndex).isAnswer()) {
                Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
                noOfCorrectAnswers++;
            }
            else {
                Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
            }
        }
        //move to next question if it exists
        //if not, display result
        //++currentQuestionIndex;
        if(currentQuestionIndex < noOfQuestionsToDisplay-1) {
            ++currentQuestionIndex;
            percentProgress = (currentQuestionIndex * 100) /noOfQuestionsToDisplay;
        }
        else {
            DisplayResultFragment x = DisplayResultFragment.newInstance(getString(R.string.correctAnsNo,noOfCorrectAnswers,noOfQuestionsToDisplay),noOfCorrectAnswers);
            x.listener = MainActivity.this;
            x.show(getSupportFragmentManager(),"Result");
            currentQuestionIndex = 0;
            noOfCorrectAnswers = 0;
            percentProgress = 0;
            shuffledQuestionsArrayList = new QuestionBank().getQuestions(this);
            if(userAddedQuestionsList != null)
                shuffledQuestionsArrayList.addAll(userAddedQuestionsList);
            Collections.shuffle(shuffledQuestionsArrayList);
            ((AppLevelVars)getApplication()).setQuestions(shuffledQuestionsArrayList);
        }
        QuestionFragment fragment = QuestionFragment.newInstance(
                shuffledQuestionsArrayList.get(currentQuestionIndex).getText(),
                shuffledQuestionsArrayList.get(currentQuestionIndex).getColor());
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
        ((AppLevelVars) getApplication()).setCurrentQuestionIndex(currentQuestionIndex);
        ((AppLevelVars) getApplication()).setNoOfCorrectAnswers(noOfCorrectAnswers);
        progressBar.setProgress(percentProgress);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.quiz_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.average_menu) {
            ArrayList<Integer> results = fm.getResultsFromFile(MainActivity.this);
            int sum=0;
            int totalResponses = 0;
            if(results != null) {
                totalResponses = results.size();
                for (int i = 0; i < results.size(); i++) {
                    sum += results.get(i);
                }
            }
            String average = getString(R.string.average,sum,totalResponses);
            AverageAlert.newInstance(average).show(getSupportFragmentManager(),"Average");
            return true;
        }
        else if(item.getItemId() == R.id.no_questions_menu) {
            SetNoOfQuestionsFragment fragment = new SetNoOfQuestionsFragment();
            fragment.listener = MainActivity.this;
            fragment.show(getSupportFragmentManager(),"NoOfQuestions");
            return true;
        }
        else if(item.getItemId() == R.id.reset_result_menu) {
            ResetAlert resetFragment = new ResetAlert();
            resetFragment.listener = MainActivity.this;
            resetFragment.show(getSupportFragmentManager(),"Reset");
            return true;
        }
        else if(item.getItemId() == R.id.add_ques_menu) {
            AddQuestionFragment fragment = new AddQuestionFragment();
            fragment.listener = MainActivity.this;
            fragment.show(getSupportFragmentManager(),"AddQuestion");
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public void radioButtonClicked(int x) {
        noOfQuestionsToDisplay = x;
        ((AppLevelVars)getApplication()).setNoOfQuestions(x);
        percentProgress = (currentQuestionIndex * 100) /noOfQuestionsToDisplay;
        progressBar.setProgress(percentProgress);
        Toast.makeText(this, getString(R.string.resetNo,x), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteResults() {
        fm.deleteAllResultsFromFile(MainActivity.this);
        Toast.makeText(this,getString(R.string.deleted),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveResult(int no) {
        fm.writeResultToFile(MainActivity.this,no);
        Toast.makeText(this,getString(R.string.added),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addQuestion(String question, boolean answer) {
        Question newQues = new Question(question,answer,R.color.Pink);
        userAddedQuestionsList.add(newQues);
        shuffledQuestionsArrayList.add(newQues);
        ((AppLevelVars)getApplication()).setQuestions(shuffledQuestionsArrayList);
        noOfQuestionsToDisplay = shuffledQuestionsArrayList.size();
        ((AppLevelVars)getApplication()).setNoOfQuestions(shuffledQuestionsArrayList.size());
        Toast.makeText(this, getString(R.string.quesAdded,noOfQuestionsToDisplay), Toast.LENGTH_SHORT).show();
    }

}