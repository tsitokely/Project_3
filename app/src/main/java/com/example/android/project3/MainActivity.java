package com.example.android.project3;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int gradeOnQuestion = 0;
    private RadioGroup mQ1FirstGroup;
    private RadioGroup mQ1SecondGroup;
    private RadioGroup mQ2FirstGroup;
    private RadioGroup mQ2SecondGroup;
    private boolean isChecking = true;
    public int mCheckedQ1Id = R.id.Q11;
    public int mCheckedQ2Id = R.id.Q21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // make sure that the different radio groups in questions 1 are not accessed at the same time
        mQ1FirstGroup = findViewById(R.id.R11);
        mQ1SecondGroup = findViewById(R.id.R12);
        mQ2FirstGroup = findViewById(R.id.R21);
        mQ2SecondGroup = findViewById(R.id.R22);

        // Once a radio button on a radio group in Q1 is clicked, other radio shouldn't bet active
        mQ1FirstGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1 && isChecking) {
                    isChecking = false;
                    mQ1SecondGroup.clearCheck();
                    mCheckedQ1Id = checkedId;
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
                    mCheckedQ1Id = checkedId;
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
                    mCheckedQ2Id = checkedId;
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
                    mCheckedQ2Id = checkedId;
                }
                isChecking = true;
            }
        });
    }

    /**
     * Once the button get result is clicked, the app starts the computation of score
     */
    public void getResults(View view) {
        for (int i = 0; i <= 4; i++) {
            // loop through all questions on the quiz
            checkQuestion(i);
        }
        // Display the toast message
        String result = getString(R.string.score_toast, (gradeOnQuestion / 4.0) * 100.0);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        // Reset the score once the current score is computed - could have used reset button instead
        gradeOnQuestion = 0;
    }

    /**
     * Compute the score of the user depending on the question
     *
     * @param questionNumber the Question number that drives the score calculation logic
     */
    public void checkQuestion(int questionNumber) {
        if (questionNumber == 1) {
            // Figure out if the user choose the correct answer on Question 1
            RadioButton getRadioQ1State = findViewById(R.id.Q13);
            boolean q1IsRight = getRadioQ1State.isChecked();
            if (q1IsRight) {
                gradeOnQuestion += 1; // if yes add one point
            }
        } else if (questionNumber == 2) {
            // Figure out if the user choose the correct answer on Question 2
            RadioButton getRadioQ2State = findViewById(R.id.Q21);
            boolean q2IsRight = getRadioQ2State.isChecked();
            if (q2IsRight) {
                gradeOnQuestion += 1; // if yes add one point
            }
        } else if (questionNumber == 3) {
            // Figure out if the user choose the correct answers on Question 3
            CheckBox getRadioQ32State = findViewById(R.id.Q32);
            CheckBox getRadioQ34State = findViewById(R.id.Q34);
            boolean q32IsRight = getRadioQ32State.isChecked();
            boolean q34IsRight = getRadioQ34State.isChecked();
            if (q32IsRight & q34IsRight) {
                gradeOnQuestion += 1; // if yes add one point
            }
        } else if (questionNumber == 4) {
            // Figure out if the user inputted the correct answer on Question 4
            EditText getTextQ4 = findViewById(R.id.Q4);
            String q4CorrectString = getTextQ4.getText().toString();
            if (q4CorrectString.trim().toLowerCase().equals("hokage")) {
                gradeOnQuestion += 1; // if yes add one point
            }
        }
    }
}
