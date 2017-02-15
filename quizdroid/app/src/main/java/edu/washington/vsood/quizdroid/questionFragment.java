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
    public int ActualAnswer;
    public int isAnswer;
    private Topic Topics;
    private Question QuestionList;




    public questionFragment() {
        // Required empty public constructor
    }

    public questionFragment(Topic topics) {
        Topics = topics;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            QuestionNumber  = getArguments().getInt("questions");
            CorrectAnswers = getArguments().getInt("correct");
            SelectedAnswer = getArguments().getInt("selectedAnswer");
            isAnswer = getArguments().getInt("isAnswer");
            QuestionList = Topics.getQuestions().get(QuestionNumber-1);

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

        question.setText(QuestionList.getQuestion());
        a.setText(QuestionList.getOption1());
        b.setText(QuestionList.getOption2());
        c.setText(QuestionList.getOption3());
        d.setText(QuestionList.getOption4());


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                char ch = 'h';
                if (isAnswer == 0) {
                    char answer = SubmittedAnswer.toLowerCase().charAt(0);
                    int pos = answer - 'a' +1;
                    ActualAnswer = QuestionList.getAnswer();
                    if (pos == ActualAnswer) {
                        CorrectAnswers++;
                    }
                }

                int nextQuestion = QuestionNumber;

                if (nextQuestion <= Topics.getQuestions().size()) {
                    if (hostActivity instanceof OverviewQuestionAnswerActivity) {
                        ((OverviewQuestionAnswerActivity) hostActivity).loadAnswerFragment(
                                nextQuestion, CorrectAnswers, SelectedAnswer, ActualAnswer, 1,
                                QuestionList.getQuestion(), QuestionList.getOption1(),
                                QuestionList.getOption2(), QuestionList.getOption3(), QuestionList.getOption4(),
                                Topics.getQuestions().size());
                    }

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
