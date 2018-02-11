package com.tj.flashcards;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tj.flashcards.DatabasePackage.API.AddLesson;
import com.tj.flashcards.DatabasePackage.Lesson;

public class EditActivity extends AppCompatActivity {
    private String title = "s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

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
                    // TODO, ensure title doesn't already exist
                    // I think this is handled in onConflict thing

                    // display title
                    TextView lessonTitle = (TextView) findViewById(R.id.editLessonTitle);
                    lessonTitle.setText(input.getText().toString());

                    // write title to database
                    Lesson toInsert = new Lesson();
                    toInsert.setTitle(input.getText().toString());
                    new AddLesson().execute(toInsert);
                }
            });

            builder.show();
        } else {

            // query for lesson title
        }


    }
}
