package edu.washington.vsood.quizdroid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    public static List<Topic> topics;
    public ListView listView;
    public String URL;
    public int interval;
    static Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        connectivity();
        QuizApp quizApp = (QuizApp) getApplication();
        if (topics == null || topics.isEmpty()) {
            topics = new ArrayList<Topic>();
            new ObtainASync().execute();
        }
        ListView listView = (ListView) findViewById(R.id.list_view);

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), OverviewQuestionAnswerActivity.class);
                String topic = (String) adapterView.getItemAtPosition(position);
                intent.putExtra("message", position);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(clickListener);

    }


    public void connectivity(){
        boolean airplanemode = isAirplaneModeOn(this);
        ConnectivityManager connect = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connect.getActiveNetworkInfo();
        if(airplanemode){
            new AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.AppTheme))
                    .setMessage("Airplane Mode is Activated. Would you like to disable it?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS));
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

        } else if (network == null || !network.isConnectedOrConnecting()){
            new AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.AppTheme))
                    .setMessage("Error: Could Not Connect To Network. Please connect to network to download data.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            activity.finish();
                            System.exit(0);
                        }
                    })
                    .show();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_settings:
                preferences();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
            URL = sharedPrefs.getString("location", "http://tednewardsandbox.site44.com/questions.json");
            interval = Integer.parseInt(sharedPrefs.getString("minutes", "10"));
            if (URL != null) {
                if (URL.equals("")) {
                    URL = "http://tednewardsandbox.site44.com/questions.json";
                }
            }
            QuizApp.getInstance().setUrl(URL);
            QuizApp.getInstance().setInterval(interval);
        }
        update();
    }

    private void preferences() {
        Intent prefs = new Intent(getApplicationContext(), edu.washington.vsood.quizdroid.Preferences.class);
        startActivityForResult(prefs, 1);
    }


    public void update() {
        QuizApp.getInstance().startAlarm(interval, URL);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }





    private class ObtainASync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // let repository fetch JSON topics in the background
            ((QuizApp) getApplication()).getRepo().obtainData(MainActivity.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // put parsed JSON into RecyclerView
            final List<Topic> topics = ((QuizApp) getApplication()).getRepo().getTopics();
            ArrayList<String> StringTopics = new ArrayList<String>();

            for(int i =0; i < topics.size(); i++) {
                StringTopics.add(topics.get(i).getTitle());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, StringTopics);
            listView = (ListView) findViewById(R.id.list_view);
            listView.setAdapter(adapter);
        }
    }

    private class TopicView extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TopicView(View v) {
            super(v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(), OverviewQuestionAnswerActivity.class);
            intent.putExtra("message", getAdapterPosition());
            startActivity(intent);
        }
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isAirplaneModeOn(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

}
