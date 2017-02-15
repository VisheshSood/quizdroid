package edu.washington.vsood.quizdroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

}
