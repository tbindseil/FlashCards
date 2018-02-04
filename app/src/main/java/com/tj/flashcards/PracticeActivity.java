package com.tj.flashcards;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tj.flashcards.DatabasePackage.AppDatabase;
import com.tj.flashcards.DatabasePackage.Lesson;

/**
 * Created by TJ on 1/28/2018.
 */

public class PracticeActivity extends AppCompatActivity {
    private final static String TITLE = "Title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        int lessonID = getIntent().getParcelableExtra(MainActivity.LESSON_ID_FROM_MAIN_ACTIVITY);

        // get Lesson object from the database
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, MainActivity.DATABASE_NAME).build();

        Lesson currentLesson = db.lessonDao().getLesson(lessonID);

        // display title
        EditText title = (EditText)findViewById(R.id.lessonTitle);

        title.setText(currentLesson.getTitle());
    }
}