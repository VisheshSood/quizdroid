package edu.washington.vsood.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Question extends AppCompatActivity {

    public int QuestionNumber;
    public int CorrectAnswers;
    public String SubmittedAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        final Button submitButton = (Button) findViewById(R.id.submit);
        submitButton.setVisibility(View.INVISIBLE);

        RadioGroup answers = (RadioGroup) findViewById(R.id.answers);

        RadioButton a = (RadioButton) findViewById(R.id.answer_a);
        RadioButton b = (RadioButton) findViewById(R.id.answer_b);
        RadioButton c = (RadioButton) findViewById(R.id.answer_c);
        RadioButton d = (RadioButton) findViewById(R.id.answer_d);

        Bundle bundle = getIntent().getExtras();
        QuestionNumber = bundle.getInt("question");
        CorrectAnswers = bundle.getInt("correct");

        final TextView question = (TextView) findViewById(R.id.question);

        if (QuestionNumber == 1) {
            question.setText("What is 5 X 2?");
            a.setText("10");
            b.setText("6");
            c.setText("4");
            d.setText("8");

        } else if (QuestionNumber == 2) {
            question.setText("What is 5 + 3?");
            a.setText("9");
            b.setText("8");
            c.setText("10");
            d.setText("11");
        } else {
            question.setText("What is 60 / 3?");
            a.setText("15");
            b.setText("20");
            c.setText("18");
            d.setText("17");
        }


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (QuestionNumber == 1) {
                    if (SubmittedAnswer.toLowerCase().equals("a")) {
                        CorrectAnswers++;
                    }
                } else if (QuestionNumber == 2) {
                    if (SubmittedAnswer.toLowerCase().equals("b")) {
                        CorrectAnswers++;
                    }
                } else {
                    if (SubmittedAnswer.toLowerCase().equals("b")) {
                        CorrectAnswers++;
                    }
                }

                int nextQuestion = QuestionNumber + 1;
                if (nextQuestion <= 3) {
                    Intent intent = new Intent(view.getContext(), Question.class);
                    intent.putExtra("question", nextQuestion);
                    startActivity(intent);
                } else {
                    //Finish page
                    //Intent intent = new Intent(view.getContext(), Question.class);
                    //startActivity(intent);
                }
            }
        });

        answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submitButton.setVisibility(View.VISIBLE);
                SubmittedAnswer = getResources().getResourceEntryName(checkedId);
                submitButton.setText("Submit");

            }
        });

    }


}
