package edu.washington.vsood.quizdroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class OverviewQuestionAnswerActivity extends AppCompatActivity {

    private String topic;
    private Topic Topic;
    private int QuestionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_question_answer);



        Bundle bundle = getIntent().getExtras();
        int val = bundle.getInt("message", -1);
        Topic = QuizApp.getInstance().getRepo().getTopics().get(val);
        topic = Topic.getTitle();
        QuestionNumber = Topic.getQuestions().size();

        loadOverViewFragment();


    }



    public void loadOverViewFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();


        Bundle overviewBundle = new Bundle();
        overviewBundle.putString("topic", topic);
        overviewBundle.putInt("qNumber", QuestionNumber);
        overviewFragment overview = new overviewFragment(Topic);
        overview.setArguments(overviewBundle);

        ft.replace(R.id.overview_fragment, overview);
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

        questionFragment qf = new questionFragment(Topic);
        qf.setArguments(questionBundle);

        //ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        ft.replace(R.id.overview_fragment, qf);
        ft.commit();
    }

    public void loadAnswerFragment(int qNumber, int correct, int selected, int actual, int isAnswer,
                                   String questionText, String a, String b, String c, String d, int total) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        Bundle questionBundle = new Bundle();

        questionBundle.putInt("questions", qNumber);
        questionBundle.putInt("correct", correct);
        questionBundle.putInt("selectedAnswer", selected);
        questionBundle.putInt("actualAnswer", actual);
        questionBundle.putInt("isAnswer", isAnswer);
        questionBundle.putString("questionText", questionText);
        questionBundle.putString("a", a);
        questionBundle.putString("b", b);
        questionBundle.putString("c", c);
        questionBundle.putString("d", d);
        questionBundle.putInt("total", total);



        answerFragment af = new answerFragment();
        af.setArguments(questionBundle);

        ft.replace(R.id.overview_fragment, af);
        ft.commit();
    }
}
