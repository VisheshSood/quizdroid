package edu.washington.vsood.quizdroid;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishesh on 2/11/17.
 */

public class QuizApp extends Application {
    private static QuizApp singleton;
    public List<Topic> topic;
    private static ObtainRepository repo;
    public String URL;
    public int interval;


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
        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        URL = sharedPrefs.getString("location", "http://tednewardsandbox.site44.com/questions.json");
        interval = Integer.parseInt(sharedPrefs.getString("minutes", "10"));
        if (URL.equals("") || URL.equals(" ")) {
            URL = "http://tednewardsandbox.site44.com/questions.json";
        }

        Toast.makeText(getApplicationContext(), "Downloading topics, please wait.", Toast.LENGTH_LONG).show();
        repo = new ObtainRepository();
    }

   public ObtainRepository getRepo() {
       return repo;
   }

    public void setInterval (int interval) {
        this.interval = interval;
    }

    public void setUrl(String url) {
        URL = url;
    }

    public String getUrl() {
        return URL;
    }
}
