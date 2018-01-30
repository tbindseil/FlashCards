package com.tj.flashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by TJ on 1/28/2018.
 */

public class PracticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        // load particular lesson
        Intent i = getIntent();

        String help = "help";

        try {
            Lesson l = i.getParcelableExtra("lesson");
            help = l.getTitle();
        } catch (Exception e) {
            help = Lesson.getLessons().get(0).getTitle();
        }

        // display title
        EditText title = (EditText)findViewById(R.id.lessonTitle);

        title.setText(help);
    }
}