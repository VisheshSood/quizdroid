package edu.washington.vsood.quizdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class questionFragment extends Fragment {

    private Activity hostActivity;


    public int QuestionNumber;
    public int CorrectAnswers;
    public String SubmittedAnswer;
    public int SelectedAnswer;
    public int isAnswer;


    public questionFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            QuestionNumber  = getArguments().getInt("questions");
            CorrectAnswers = getArguments().getInt("correct");
            SelectedAnswer = getArguments().getInt("selectedAnswer");
            isAnswer = getArguments().getInt("isAnswer");

        }
        hostActivity = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_question, container, false);


        final Button submitButton = (Button) rootView.findViewById(R.id.submit);

        RadioGroup answers = (RadioGroup) rootView.findViewById(R.id.answers);

        RadioButton a = (RadioButton) rootView.findViewById(R.id.a);
        RadioButton b = (RadioButton) rootView.findViewById(R.id.b);
        RadioButton c = (RadioButton) rootView.findViewById(R.id.c);
        RadioButton d = (RadioButton) rootView.findViewById(R.id.d);


        a.setEnabled(true);
        b.setEnabled(true);
        c.setEnabled(true);
        d.setEnabled(true);



        submitButton.setText("Submit");
        submitButton.setVisibility(View.INVISIBLE);


        final TextView question = (TextView) rootView.findViewById(R.id.question);

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

                if (nextQuestion <= 3) {
                    if (hostActivity instanceof OverviewQuestionAnswerActivity) {
                        ((OverviewQuestionAnswerActivity) hostActivity).loadAnswerFragment(nextQuestion, CorrectAnswers, SelectedAnswer, 1);
                    }

                } else if (nextQuestion > 3) {
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



        return rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}
