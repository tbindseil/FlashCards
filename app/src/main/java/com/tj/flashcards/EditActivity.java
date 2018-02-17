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
import com.tj.flashcards.DatabasePackage.API.GetCardsFromLessonID;
import com.tj.flashcards.DatabasePackage.API.GetLessonFromID;
import com.tj.flashcards.DatabasePackage.API.UpdateLesson;
import com.tj.flashcards.DatabasePackage.FlashCard;
import com.tj.flashcards.DatabasePackage.Lesson;

import java.util.Iterator;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    //private String title = "s"
    // used to save or update
    private boolean newCard;
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
                if (currLesson == null) {
                    currLesson = new Lesson();
                }
                currLesson.setTitle(input.getText().toString());

                fillInUI();
            }
        });

        builder.show();
    }

    public void onSaveLessonClicked(View view) {
        if (currLesson != null) {
            if (newCard) {
                new AddLesson().execute(currLesson);
            }
            else {
                new UpdateLesson().execute(currLesson);
            }
        }
        Intent intent = new Intent(EditActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onDeleteLessonClicked(View view) {
        if (currLesson == null) {
            Intent intent = new Intent(EditActivity.this, MainActivity.class);
            startActivity(intent);
            return;
        }

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
        private FlashCard card;

        public FlashCardListener(FlashCard card) {
            this.card = card;
        }

        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
            if (currLesson == null) {
                builder.setTitle("enter lesson title and save first!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.show();
                return;
            }

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

                    if (card == null) {
                        card = new FlashCard();
                        card.setAssociatedLessonID(currLesson.getId());
                    }

                    card.setFront(front);
                    card.setBack(back);
                    if (!currLesson.getFlashCardList().contains(card)) {
                        currLesson.getFlashCardList().add(card);
                    }
                        /* TODO save in save button
                        try {
                            new AddFlashCard().execute(toAdd).get();
                        } catch (Exception e) {

                        } */
                }
            });

            builder.show();

            // may need to be moved??
            //fillInUI();
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

        // clear all card buttons
        LinearLayout ll = findViewById(R.id.CardLayout);
        ll.removeAllViews();

        // add new card button
        Button addFlashCard = new Button(this);
        addFlashCard.setText("New Flash Card");
        addFlashCard.setOnClickListener(new FlashCardListener(null));
        ll.addView(addFlashCard);

        if (currLesson == null) {
            return;
        }

        List<FlashCard> cardList = null;
        // add button to edit all current cards
        try {
            cardList = new GetCardsFromLessonID().execute(currLesson.getId()).get();
        } catch (Exception e) {

        }

        if (cardList == null) {
            return;
        }

        Iterator<FlashCard> itr = cardList.iterator();
        Button currCardButton = new Button(this);
        while (itr.hasNext()) {
            FlashCard currCard = itr.next();
            currCardButton.setText(currCard.getFront() + ":" + currCard.getBack());
            currCardButton.setOnClickListener(new FlashCardListener(currCard));
            ll.addView(currCardButton);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        newCard = false;

        Integer lessonID = getIntent().getIntExtra(MainActivity.LESSON_ID_FROM_MAIN_ACTIVITY, -1);
        try {
            currLesson = new GetLessonFromID().execute(lessonID).get();
            if (currLesson == null) {
                newCard = true;
            }
        } catch (Exception e) {
            newCard = true;
            currLesson = null;
        }

        fillInUI();
    }
}
