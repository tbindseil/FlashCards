package com.tj.flashcards;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tj.flashcards.DatabasePackage.API.AddLesson;
import com.tj.flashcards.DatabasePackage.API.DeleteLessonByID;
import com.tj.flashcards.DatabasePackage.API.GetLessonFromID;
import com.tj.flashcards.DatabasePackage.API.UpdateLesson;
import com.tj.flashcards.DatabasePackage.Lesson;

public class EditActivity extends AppCompatActivity {
    private String title = "s";
    private Lesson currLesson = null;

    public void onEditTitleButtonClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Lesson Title:");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currLesson.setTitle(input.getText().toString());
                new UpdateLesson().execute(currLesson);

                // update button
                Button title = (Button) findViewById(R.id.editLessonTitle);
                title.setText(input.getText().toString());
            }
        });

        builder.show();
    }

    public void onSaveLessonClicked(View view) {
        Intent intent = new Intent(EditActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onDeleteLessonClicked(View view) {
        try {
            new DeleteLessonByID().execute(currLesson);
        } catch (Exception e) {
            Toast.makeText(this, "I tried to delete", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(EditActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onAbandonButtonClicked(View view) {
        Intent intent = new Intent(EditActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Integer lessonID = getIntent().getIntExtra(MainActivity.LESSON_ID_FROM_MAIN_ACTIVITY, -1);
        // display title
        Button title = (Button) findViewById(R.id.editLessonTitle);
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
}
