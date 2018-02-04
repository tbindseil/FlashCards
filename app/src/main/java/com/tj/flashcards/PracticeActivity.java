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
    private final static String TITLE = "Title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        // display title
        EditText title = (EditText)findViewById(R.id.lessonTitle);

        title.setText(TITLE);
    }
}