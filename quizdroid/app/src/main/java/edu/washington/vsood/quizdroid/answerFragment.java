package edu.washington.vsood.quizdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import static edu.washington.vsood.quizdroid.R.id.total;


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
    public int SelectedAnswer;
    public int isAnswer;
    public int ActualAnswer;
    public String QuestionText;
    public String[] Answers;
    public int Total;


    private Activity hostActivity;


    public answerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            Answers = new String[]{"","","",""};
            QuestionNumber  = getArguments().getInt("questions");
            CorrectAnswers = getArguments().getInt("correct");
            SelectedAnswer = getArguments().getInt("selectedAnswer");
            ActualAnswer = getArguments().getInt("actualAnswer");
            isAnswer = getArguments().getInt("isAnswer");
            QuestionText = getArguments().getString("questionText");
            Answers[0] = getArguments().getString("a");
            Answers[1] = getArguments().getString("b");
            Answers[2] = getArguments().getString("c");
            Answers[3] = getArguments().getString("d");
            Total = getArguments().getInt("total");
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

        if (isAnswer == 1 && QuestionNumber < Total) {
            submitButton.setVisibility(View.VISIBLE);
            submitButton.setText("NEXT");
        } else if(isAnswer == 1 && QuestionNumber == Total) {
            submitButton.setVisibility(View.VISIBLE);
            submitButton.setText("FINISH");
        } else {
            submitButton.setText("Submit");
            submitButton.setVisibility(View.INVISIBLE);
        }

        final TextView question = (TextView) rootView.findViewById(R.id.question);

        question.setText(QuestionText);
        a.setText(Answers[0]);
        b.setText(Answers[1]);
        c.setText(Answers[2]);
        d.setText(Answers[3]);

        if (isAnswer == 1) {
            RadioButton wrong = (RadioButton) rootView.findViewById(SelectedAnswer);
            wrong.setTextColor(Color.RED);
            if (ActualAnswer == 1) {
                a.setTextColor(Color.GREEN);
            } else if (ActualAnswer == 2) {
                b.setTextColor(Color.GREEN);
            } else if (ActualAnswer == 3) {
                c.setTextColor(Color.GREEN);
            } else if (ActualAnswer == 4) {
                d.setTextColor(Color.GREEN);
            }

            TextView total = (TextView) rootView.findViewById(R.id.total);
            total.setText("You have answered " + CorrectAnswers + " out of " + QuestionNumber +" answers correctly.");
        }


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextQuestion = QuestionNumber;
                nextQuestion++;
                if (nextQuestion <= Total) {
                    if (hostActivity instanceof OverviewQuestionAnswerActivity) {
                        ((OverviewQuestionAnswerActivity) hostActivity).loadQuestionFragment(nextQuestion , CorrectAnswers, SelectedAnswer, 0);
                    }

                } else if (nextQuestion > Total) {
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
