package com.tj.flashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by TJ on 1/28/2018.
 */

public class PracticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        Intent i = getIntent();
        Lesson l = i.getParcelableExtra("lesson");


    }
}