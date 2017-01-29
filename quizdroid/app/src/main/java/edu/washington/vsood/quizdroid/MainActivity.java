package edu.washington.vsood.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public ArrayList<String> topics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        topics.add("Math");
        topics.add("Physics");
        topics.add("Marvel Super Heroes");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics);


        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), Overview.class);
                String topic = (String) adapterView.getItemAtPosition(position);
                intent.putExtra("message", topic);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(clickListener);

    }
}
