package edu.washington.vsood.quizdroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class OverviewQuestionAnswerActivity extends AppCompatActivity {

    private String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_question_answer);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();


        Bundle bundle = getIntent().getExtras();
        topic = bundle.getString("message");

        overviewFragment of = new overviewFragment();
        of.setArguments(bundle);
        //ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.add(R.id.overview_fragment, of);
        ft.commit();


    }


    public void loadQuestionFragment(int qNumber, int correct, int selected, int isAnswer) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        Bundle questionBundle = new Bundle();

        questionBundle.putInt("questions", qNumber);
        questionBundle.putInt("correct", correct);
        questionBundle.putInt("selectedAnswer", selected);
        questionBundle.putInt("isAnswer", isAnswer);

        questionFragment qf = new questionFragment();
        qf.setArguments(questionBundle);

        //ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.overview_fragment, qf);
        ft.commit();
    }

    public void loadAnswerFragment(int qNumber, int correct, int selected, int isAnswer) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        Bundle questionBundle = new Bundle();

        questionBundle.putInt("questions", qNumber);
        questionBundle.putInt("correct", correct);
        questionBundle.putInt("selectedAnswer", selected);
        questionBundle.putInt("isAnswer", isAnswer);

        answerFragment af = new answerFragment();
        af.setArguments(questionBundle);

        //ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.overview_fragment, af);
        ft.commit();
    }
}
