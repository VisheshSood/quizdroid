package edu.washington.vsood.quizdroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class overviewFragment extends Fragment {

    private Activity hostActivity;
    private String topic;

    public overviewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            topic = getArguments().getString("message");
        }

        hostActivity = getActivity();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_overview, container, false);

        Button begin = (Button) rootView.findViewById(R.id.fragment_begin);
        TextView heading = (TextView) rootView.findViewById(R.id.fragment_topic);
        TextView description = (TextView) rootView.findViewById(R.id.fragment_topic_description);
        TextView questions = (TextView) rootView.findViewById(R.id.fragment_question_count);

        heading.setText(topic);
        initializeDescriptionAndQuestions(topic, description, questions);

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hostActivity instanceof OverviewQuestionAnswerActivity) {
                    ((OverviewQuestionAnswerActivity) hostActivity).loadQuestionFragment(1, 0, 0, 0);
                }
            }
        });

        return rootView;

    }

    private void initializeDescriptionAndQuestions(String topic, TextView des, TextView questions) {

        if (topic.equals("Math")) {
            des.setText("You have chosen the Math topic. In this topic you will be asked about simple " +
                    "Math questions like addition, subtraction, multiplication and division!");
            questions.setText("There are 3 questions in this section.");
        } else if (topic.equals("Physics")) {
            des.setText("You have chosen the Physics topic. In this topic you will be asked about simple " +
                    "Physics questions like random equations and definitions!");
            questions.setText("There are 3 questions in this section.");
        } else {
            des.setText("You have chosen the Marvel Super Heroes topic. In this topic you will be asked " +
                    "about simple Super Hero questions regarding different comic book characters");
            questions.setText("There are 3 questions in this section.");

        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }


}
