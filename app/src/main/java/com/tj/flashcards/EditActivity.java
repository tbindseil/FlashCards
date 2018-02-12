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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tj.flashcards.DatabasePackage.API.AddFlashCard;
import com.tj.flashcards.DatabasePackage.API.AddLesson;
import com.tj.flashcards.DatabasePackage.API.DeleteLessonByID;
import com.tj.flashcards.DatabasePackage.API.GetLessonFromID;
import com.tj.flashcards.DatabasePackage.API.UpdateLesson;
import com.tj.flashcards.DatabasePackage.FlashCard;
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
                if (currLesson != null) {
                    currLesson.setTitle(input.getText().toString());
                }
                try {
                    new UpdateLesson().execute(currLesson).get();
                } catch (Exception e) {

                }

                fillInUI();
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

    public class FlashCardListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
            builder.setTitle("Enter New Card as <front>:<back>");

            // Set up the input
            final EditText input = new EditText(EditActivity.this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO check formatting
                    String[] tokens = input.getText().toString().split(":");
                    String front = tokens[0];
                    String back = tokens[1];

                    FlashCard toAdd = new FlashCard();
                    toAdd.setFront(front);
                    toAdd.setBack(back);
                    toAdd.setAssociatedLessonID(currLesson.getId());

                    try {
                        new AddFlashCard().execute(toAdd).get();
                    } catch (Exception e) {

                    }

                    fillInUI();
                }
            });

            builder.show();
        }
    }

    // called after any changes are made and by onCreate
    private void fillInUI() {
        Button title = (Button) findViewById(R.id.editLessonTitle);
        if (currLesson == null) {
            title.setText("Add Lesson Title");
        } else {
            title.setText(currLesson.getTitle());
        }

        // TODO: clear list of flashcards

        Button addFlashCard = new Button(this);
        addFlashCard.setText("New Flash Card");
        addFlashCard.setOnClickListener(new FlashCardListener());
        LinearLayout ll = findViewById(R.id.buttonLayout);
        ll.addView(addFlashCard);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Integer lessonID = getIntent().getIntExtra(MainActivity.LESSON_ID_FROM_MAIN_ACTIVITY, -1);
        try {
            currLesson = new GetLessonFromID().execute(lessonID).get();
        } catch (Exception e) {
            currLesson = null;
        }

        fillInUI();
    }
}
