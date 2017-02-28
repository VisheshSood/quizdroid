package edu.washington.vsood.quizdroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishesh on 2/14/17.
 */

public class ObtainRepository implements TopicRepository {

    public final static String TAG = "ObtainRepository";
    public List<Topic> TOPICSLIST = new ArrayList<>();
    private String url;

    public ObtainRepository() {
    }

    public void obtainData(Activity activity) {
//        if (TOPICSLIST.size() > 0) {
//            return;
//        }
        TOPICSLIST = new ArrayList<Topic>();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        url = prefs.getString("location", "https://tednewardsandbox.site44.com/questions.json");
        if (url == "" || url == null) {
            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.putString("location", "https://tednewardsandbox.site44.com/questions.json");
            prefEditor.commit();
            url = "https://tednewardsandbox.site44.com/questions.json";
        }

        WebHandler sh = new WebHandler();

        String jsontext = sh.makeServiceCall(url);

        if (jsontext != null) {
            try {
                // Getting JSON Array node
                JSONArray topics = new JSONArray(jsontext);

                Log.i("QuizApp", "Downloading Topics!");
                for (int i = 0; i < topics.length(); i++) {
                    JSONObject topicsList = topics.getJSONObject(i);

                    JSONArray qs = topicsList.getJSONArray("questions");
                    List<Question> questions = new ArrayList<Question>();
                    for (int j = 0; j < qs.length(); j++) {
                        JSONObject q = qs.getJSONObject(j);
                        questions.add(new Question(q.getString("text"),
                                q.getJSONArray("answers").getString(0),
                                q.getJSONArray("answers").getString(1),
                                q.getJSONArray("answers").getString(2),
                                q.getJSONArray("answers").getString(3),
                                q.getInt("answer")));

                    }
                    String title = topicsList.getString("title");
                    String desc = topicsList.getString("desc");
                    TOPICSLIST.add(new Topic(title, desc, desc, questions));
                }
            } catch (final JSONException e) {
            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Topic> getTopics() {
        return TOPICSLIST;
    }


}
