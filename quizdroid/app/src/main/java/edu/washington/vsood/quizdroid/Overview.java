package edu.washington.vsood.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Overview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        Bundle bundle = getIntent().getExtras();
        String topic = bundle.getString("message");

        Button begin = (Button) findViewById(R.id.begin);
        TextView heading = (TextView) findViewById(R.id.topic);
        TextView description = (TextView) findViewById(R.id.topic_description);
        TextView questions = (TextView) findViewById(R.id.question_count);

        heading.setText(topic);
        initializeDescriptionAndQuestions(topic, description, questions);

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Question.class);
                startActivity(intent);
            }
        });

    }

    private void initializeDescriptionAndQuestions(String topic, TextView des, TextView questions) {

        if (topic.equals("Math")) {
            des.setText("You have chosen the Math topic. In this topic you will be asked about simple " +
                    "Math questions like addition, subtraction, multiplication and division!");
            questions.setText("There are 10 questions in this section.");
        } else if (topic.equals("Physics")) {
            des.setText("You have chosen the Physics topic. In this topic you will be asked about simple " +
                    "Physics questions like random equations and definitions!");
            questions.setText("There are 5 questions in this section.");
        } else {
            des.setText("You have chosen the Marvel Super Heroes topic. In this topic you will be asked " +
                    "about simple Super Hero questions regarding different comic book characters");
            questions.setText("There are 7 questions in this section.");

        }

    }
}
