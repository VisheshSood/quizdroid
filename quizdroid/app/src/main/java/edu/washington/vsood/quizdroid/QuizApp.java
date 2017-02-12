package edu.washington.vsood.quizdroid;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishesh on 2/11/17.
 */

public class QuizApp extends Application implements TopicRepository {
    private static QuizApp singleton;
    public List<Topic> topic;


    public QuizApp()
    {
        if (singleton == null)
            singleton = this;
        else {
            Log.e("QuizApp", "There is already a QuizApp running!");
            throw new RuntimeException();
        }
        topic = new ArrayList<Topic>();
    }

    public static QuizApp getInstance(){
        return singleton;
    }


    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("QuizApp onCreate()", "onCreate triggered");
    }

    @Override
    public List<Topic> getTopics() {
        return topic;
    }
}
