package com.example.android.project3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int gradeOnQuestion;
    private RadioGroup mQ1FirstGroup;
    private RadioGroup mQ1SecondGroup;
    private RadioGroup mQ2FirstGroup;
    private RadioGroup mQ2SecondGroup;
    private boolean isChecking = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // make sure that the different radio groups in questions 1 are not accessed at the same time
        mQ1FirstGroup = findViewById(R.id.question_1_1_rg);
        mQ1SecondGroup = findViewById(R.id.question_1_2_rg);
        mQ2FirstGroup = findViewById(R.id.question_2_1_rg);
        mQ2SecondGroup = findViewById(R.id.question_2_2_rg);

        // Once a radio button on a radio group in Q1 is clicked, other radio shouldn't bet active
        mQ1FirstGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    mQ1SecondGroup.clearCheck();
                }
                isChecking = true;
            }
        });

        mQ1SecondGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    mQ1FirstGroup.clearCheck();
                }
                isChecking = true;
            }
        });

        // Once a radio button on a radio group in Q2 is clicked, other radio shouldn't bet active
        mQ2FirstGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    mQ2SecondGroup.clearCheck();
                }
                isChecking = true;
            }
        });

        mQ2SecondGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    mQ2FirstGroup.clearCheck();
                }
                isChecking = true;
            }
        });
    }

    /**
     * Once the button get result is clicked, the app starts the computation of score
     */
    public void getResults(View view) {
        for (int i = 1; i <= 4; i++) {
            // loop through all questions on the quiz
            checkQuestion(i);
        }
        if (gradeOnQuestion >= 2) {
            // Display a toast message to say that the user won
            String result = getString(R.string.score_toast_win, (gradeOnQuestion / 4.0) * 100.0);
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        } else {
            // Display a toast message to say that the user won
            String result = getString(R.string.score_toast_loose, (gradeOnQuestion / 4.0) * 100.0);
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }

        // Reset the score once the current score is computed - could have used reset button instead
        gradeOnQuestion = 0;
    }

    /**
     * Compute the score of the user depending on the question
     *
     * @param questionNumber the Question number that drives the score calculation logic
     */
    public void checkQuestion(int questionNumber) {
        switch (questionNumber) {
            case 1:
                // Figure out if the user choose the correct answer on Question 1
                RadioButton getRadioQ1State = findViewById(R.id.question_1_answer_3_rb);
                boolean q1IsRight = getRadioQ1State.isChecked();
                if (q1IsRight) {
                    gradeOnQuestion += 1; // if yes add one point
                }
                break;
            case 2:
                // Figure out if the user choose the correct answer on Question 2
                RadioButton getRadioQ2State = findViewById(R.id.question_2_answer_1_rb);
                boolean q2IsRight = getRadioQ2State.isChecked();
                if (q2IsRight) {
                    gradeOnQuestion += 1; // if yes add one point
                }
                break;
            case 3:
                // Figure out if the user choose the correct answers on Question 3
                CheckBox getRadioQ31State = findViewById(R.id.question_3_answer_1_cb);
                CheckBox getRadioQ32State = findViewById(R.id.question_3_answer_2_cb);
                CheckBox getRadioQ33State = findViewById(R.id.question_3_answer_3_cb);
                CheckBox getRadioQ34State = findViewById(R.id.question_3_answer_4_cb);

                if (!getRadioQ31State.isChecked() && getRadioQ32State.isChecked() && !getRadioQ33State.isChecked() && getRadioQ34State.isChecked()) {
                    gradeOnQuestion += 1; // if yes add one point
                }
                break;
            case 4:
                // Figure out if the user inputted the correct answer on Question 4
                EditText getTextQ4 = findViewById(R.id.question_4_et);
                String q4CorrectString = getTextQ4.getText().toString();
                if (q4CorrectString.trim().equalsIgnoreCase("hokage")) {
                    gradeOnQuestion += 1; // if yes add one point
                }
                break;
            default:
                gradeOnQuestion += 0;
        }

    }
}
