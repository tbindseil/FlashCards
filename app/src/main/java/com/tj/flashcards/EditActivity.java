package com.tj.flashcards;

import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tj.flashcards.DatabasePackage.API.AddLesson;
import com.tj.flashcards.DatabasePackage.API.DeleteLessonByID;
import com.tj.flashcards.DatabasePackage.API.GetLessonFromID;
import com.tj.flashcards.DatabasePackage.Lesson;

public class EditActivity extends AppCompatActivity {
    private String title = "s";
    private Lesson currLesson = null;

    public void onDeleteLessonClicked(View view) {
        Integer lessonId = getIntent().getIntExtra(MainActivity.LESSON_ID_FROM_MAIN_ACTIVITY, -1);
        try {
            new DeleteLessonByID().execute(currLesson);
        } catch (Exception e) {
            Toast.makeText(this, "I tried to delete", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Integer lessonID = getIntent().getIntExtra(MainActivity.LESSON_ID_FROM_MAIN_ACTIVITY, -1);
        // display title
        EditText title = (EditText)findViewById(R.id.editLessonTitle);
        if (lessonID == -1) {
            title.setText("<Add Lesson Title>");
            currLesson = new Lesson();
        } else {
            try {
                currLesson = new GetLessonFromID().execute(lessonID).get();
                title.setText(currLesson.getTitle());
            } catch (Exception e) {
                Toast.makeText(this, "I tried", Toast.LENGTH_LONG).show();
            }
        }
    }

        // display cards


/*
        int lessonID = getIntent().getIntExtra(MainActivity.LESSON_ID_FROM_MAIN_ACTIVITY, -1);
        if (lessonID == -1) {
            // give lesson a title
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Lesson Title:");

            // Set up the input
            final EditText input = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Lesson toInsert = new Lesson();

                    Boolean result = false;
                    do {
                        // write title to database
                        toInsert.setTitle(input.getText().toString());
                        try {
                            result = new AddLesson().execute(toInsert).get();
                        } catch (Exception e) {

                        }
                    } while (!result);

                    // display title
                    TextView lessonTitle = (TextView) findViewById(R.id.editLessonTitle);
                    lessonTitle.setText(input.getText().toString());

                }
            });

            builder.show();
        } else {
            // edit stuff
        }


    }
*/
}
