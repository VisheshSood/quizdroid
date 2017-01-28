package edu.washington.vsood.quizdroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.content.Intent;
import android.view.View;


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
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(clickListener);

    }
}
