package edu.washington.vsood.quizdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static edu.washington.vsood.quizdroid.R.id.a_answers;
import static edu.washington.vsood.quizdroid.R.id.answers;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link answerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link answerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class answerFragment extends Fragment {

    public int QuestionNumber;
    public int CorrectAnswers;
    public String SubmittedAnswer;
    public int SelectedAnswer;
    public int isAnswer;

    private Activity hostActivity;


    public answerFragment() {
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

        RadioGroup answers = (RadioGroup) rootView.findViewById(R.id.a_answers);

        RadioButton a = (RadioButton) rootView.findViewById(R.id.a);
        RadioButton b = (RadioButton) rootView.findViewById(R.id.b);
        RadioButton c = (RadioButton) rootView.findViewById(R.id.c);
        RadioButton d = (RadioButton) rootView.findViewById(R.id.d);


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

        final TextView question = (TextView) rootView.findViewById(R.id.question);

        if (QuestionNumber == 1) {
            question.setText("What is 5 X 2?");
            a.setText("10");
            b.setText("6");
            c.setText("4");
            d.setText("8");

            if (isAnswer == 1) {
                RadioButton wrong = (RadioButton) rootView.findViewById(SelectedAnswer);
                wrong.setTextColor(Color.RED);
                a.setTextColor(Color.GREEN);

                TextView total = (TextView) rootView.findViewById(R.id.total);
                total.setText("You have answered " + CorrectAnswers + " out of " + QuestionNumber +" answers correctly.");
            }

        } else if (QuestionNumber == 2) {
            question.setText("What is 5 + 3?");
            a.setText("9");
            b.setText("8");
            c.setText("10");
            d.setText("11");

            if (isAnswer == 1) {
                RadioButton wrong = (RadioButton) rootView.findViewById(SelectedAnswer);
                wrong.setTextColor(Color.RED);
                b.setTextColor(Color.GREEN);

                TextView total = (TextView) rootView.findViewById(R.id.total);
                total.setText("You have answered " + CorrectAnswers + " out of " + QuestionNumber + " answers  correctly.");
            }
        } else {
            question.setText("What is 60 / 3?");
            a.setText("15");
            b.setText("20");
            c.setText("18");
            d.setText("17");

            if (isAnswer == 1) {
                RadioButton wrong = (RadioButton) rootView.findViewById(SelectedAnswer);
                wrong.setTextColor(Color.RED);
                b.setTextColor(Color.GREEN);

                TextView total = (TextView) rootView.findViewById(R.id.total);
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
                nextQuestion++;
                if (nextQuestion <= 3) {
                    if (hostActivity instanceof OverviewQuestionAnswerActivity) {
                        ((OverviewQuestionAnswerActivity) hostActivity).loadQuestionFragment(nextQuestion , CorrectAnswers, SelectedAnswer, 0);
                    }

                } else if (nextQuestion > 3) {
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(intent);
                }

            }
        });




        return rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}
