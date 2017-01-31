package edu.washington.vsood.quizdroid;

import android.content.Intent;
import android.graphics.Color;
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
    public int SelectedAnswer;
    public int isAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        final Button submitButton = (Button) findViewById(R.id.submit);

        RadioGroup answers = (RadioGroup) findViewById(R.id.answers);

        RadioButton a = (RadioButton) findViewById(R.id.a);
        RadioButton b = (RadioButton) findViewById(R.id.b);
        RadioButton c = (RadioButton) findViewById(R.id.c);
        RadioButton d = (RadioButton) findViewById(R.id.d);


        Bundle bundle = getIntent().getExtras();
        QuestionNumber = bundle.getInt("question");
        CorrectAnswers = bundle.getInt("correct");
        isAnswer = bundle.getInt("isAnswer");
        SelectedAnswer = bundle.getInt("selectedAnswer");

        if (isAnswer == 1) {
            a.setEnabled(false);
            b.setEnabled(false);
            c.setEnabled(false);
            d.setEnabled(false);

        }
        if (isAnswer == 1 && QuestionNumber != 3) {
            submitButton.setVisibility(View.VISIBLE);
            submitButton.setText("NEXT");
        } else if(isAnswer == 1 && QuestionNumber == 3) {
            submitButton.setVisibility(View.VISIBLE);
            submitButton.setText("FINISH");
        } else {
            submitButton.setText("Submit");
            submitButton.setVisibility(View.INVISIBLE);
        }

        final TextView question = (TextView) findViewById(R.id.question);

        if (QuestionNumber == 1) {
            question.setText("What is 5 X 2?");
            a.setText("10");
            b.setText("6");
            c.setText("4");
            d.setText("8");

            if (isAnswer == 1) {
                RadioButton wrong = (RadioButton) findViewById(SelectedAnswer);
                wrong.setTextColor(Color.RED);
                a.setTextColor(Color.GREEN);

                TextView total = (TextView) findViewById(R.id.total);
                total.setText("You have answered " + CorrectAnswers + " out of " + QuestionNumber +" answers correctly.");
            }

        } else if (QuestionNumber == 2) {
            question.setText("What is 5 + 3?");
            a.setText("9");
            b.setText("8");
            c.setText("10");
            d.setText("11");

            if (isAnswer == 1) {
                RadioButton wrong = (RadioButton) findViewById(SelectedAnswer);
                wrong.setTextColor(Color.RED);
                b.setTextColor(Color.GREEN);

                TextView total = (TextView) findViewById(R.id.total);
                total.setText("You have answered " + CorrectAnswers + " out of " + QuestionNumber + " answers  correctly.");
            }
        } else {
            question.setText("What is 60 / 3?");
            a.setText("15");
            b.setText("20");
            c.setText("18");
            d.setText("17");

            if (isAnswer == 1) {
                RadioButton wrong = (RadioButton) findViewById(SelectedAnswer);
                wrong.setTextColor(Color.RED);
                b.setTextColor(Color.GREEN);

                TextView total = (TextView) findViewById(R.id.total);
                total.setText("You have answered " + CorrectAnswers + " out of " + QuestionNumber + " answers correctly.");
            }
        }


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAnswer == 0) {
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
                }

                int nextQuestion = QuestionNumber;
                if (isAnswer == 1) {
                    nextQuestion++;
                }
                if (nextQuestion <= 3) {
                    Intent intent = new Intent(view.getContext(), Question.class);
                    intent.putExtra("question", nextQuestion);
                    if (isAnswer == 0) {
                        intent.putExtra("isAnswer", 1);
                    } else {
                        intent.putExtra("isAnswer", 0);
                    }
                    intent.putExtra("selectedAnswer", SelectedAnswer);
                    intent.putExtra("correct", CorrectAnswers);
                    startActivity(intent);
                } else if (nextQuestion > 3){
                    //Finish page
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                submitButton.setVisibility(View.VISIBLE);
                SubmittedAnswer = getResources().getResourceEntryName(checkedId);
                SelectedAnswer = checkedId;
                submitButton.setText("Submit");

            }
        });

    }

}
