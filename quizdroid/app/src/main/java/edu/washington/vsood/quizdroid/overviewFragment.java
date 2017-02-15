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

    public Activity hostActivity;
    public String topic;
    public Topic selectedTopic;
    public int QuestionNumber;


    public overviewFragment() {

    }

    public overviewFragment(Topic selected) {
        selectedTopic = selected;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            topic = getArguments().getString("topic");
            QuestionNumber = getArguments().getInt("qNumber");
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

        heading.setText(topic + " Overview");
        description.setText(selectedTopic.getLong());
        questions.setText("There are " + QuestionNumber + " questions in this section.");

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




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }


}
