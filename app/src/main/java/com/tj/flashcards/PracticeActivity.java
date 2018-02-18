package com.tj.flashcards;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tj.flashcards.DatabasePackage.API.GetLessonFromID;
import com.tj.flashcards.DatabasePackage.AppDatabase;
import com.tj.flashcards.DatabasePackage.FlashCard;
import com.tj.flashcards.DatabasePackage.Lesson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by TJ on 1/28/2018.
 */

public class PracticeActivity extends AppCompatActivity {
    private Semaphore textEntrySem;
    private Handler practiceHandler;

    // new stuff
    public void handleState(StringMessage strMessage) {
        Message newMessage = practiceHandler.obtainMessage();
        newMessage.obj = strMessage;
        newMessage.sendToTarget();
    }

    public void onEnterButtonClicked(View view) {
        Button thisButton = (Button)view;
        if (!thisButton.getText().equals("Return")) {
            textEntrySem.release();
        }
        else {
            Intent intent = new Intent(PracticeActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        int lessonID = getIntent().getIntExtra(MainActivity.LESSON_ID_FROM_MAIN_ACTIVITY, -1);

        // set up control flow
        textEntrySem = new Semaphore(0);
        practiceHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message inputMessage) {
                StringMessage stringMessage = (StringMessage)inputMessage.obj;
                stringMessage.getView().setText(stringMessage.getString());
            }
        };

        Thread runPractice = new RunActivity(lessonID);
        runPractice.start();
    }

    class RunActivity extends Thread {
        private Lesson currLesson;

        public RunActivity(int lessonID) {
            if (lessonID == -1) {
                return;
            }

            try {
                currLesson = new GetLessonFromID().execute(lessonID).get();
            } catch (Exception e) {
                return;
            }

            currLesson.updateFlashCardList();

            // display title
            EditText title = (EditText)findViewById(R.id.lessonTitle);
            StringMessage stringMessage = new StringMessage(title, currLesson.getTitle());
            PracticeActivity.this.handleState(stringMessage);
        }

        @Override
        public void run() {
            TextView front = findViewById(R.id.frontView);
            EditText back = findViewById(R.id.backEditText);

            List<FlashCard> cardsToRetry = new ArrayList<FlashCard>();
            StringMessage stringMessage = null;
            do {
                cardsToRetry = new ArrayList<FlashCard>();
                Iterator<FlashCard> cardItr = currLesson.getFlashCardList().iterator();
                while (cardItr.hasNext()) {
                    //boolean deubg = false;
                    //for (int i = 0; i < currLesson.getFlashCardList().size(); i++) {
                    // debubg
                    //  if (!deubg) {
                    //   TextView title = findViewById(R.id.lessonTitle);
                    // stringMessage = new StringMessage(title, "i is " + i);
                    //PracticeActivity.this.handleState(stringMessage);
                    //}

                    //FlashCard currCard = currLesson.getFlashCardList().get(i);
                    FlashCard currCard = cardItr.next();
                    stringMessage = new StringMessage(front, currCard.getFront());
                    PracticeActivity.this.handleState(stringMessage);
                    stringMessage = new StringMessage(back, "");
                    PracticeActivity.this.handleState(stringMessage);

                    try {
                        textEntrySem.acquire();
                    } catch (Exception e) {
                        //Toast.makeText(PracticeActivity.this, "Didn't get sem", Toast.LENGTH_LONG).show();
                    }

                    if (!back.getText().toString().equals(currCard.getBack())) {
                        // fail, add card to back of list
                        //currLesson.getFlashCardList().remove(currCard);
                        cardsToRetry.add(currCard);
                        //TextView title = findViewById(R.id.lessonTitle);
                        //stringMessage = new StringMessage(title, "cardback is " + currCard.getBack() + " and text is ");
                        //PracticeActivity.this.handleState(stringMessage);
                        //deubg = true;
                        //Toast.makeText(PracticeActivity.this, "Didn't match", Toast.LENGTH_LONG).show();
                    } else {
                        //Toast.makeText(PracticeActivity.this, "Did match", Toast.LENGTH_LONG).show();
                    }
                    cardItr.remove();
                }

                // all currLessons cards should be removed, refill with missed cards
                currLesson.getFlashCardList().addAll(cardsToRetry);
            } while (!cardsToRetry.isEmpty());

            Button enterButton = findViewById(R.id.EnterButton);
            stringMessage = new StringMessage(enterButton, "Return");
            PracticeActivity.this.handleState(stringMessage);
        }
    }

    class StringMessage {
        private TextView view;
        private String string;

        StringMessage(TextView v, String s) {
            view = v;
            string = s;
        }

        public TextView getView() {
            return view;
        }

        public String getString() {
            return string;
        }

        public void setView(TextView v) {
            view = v;
        }

        public void setString(String s) {
            string = s;
        }
    }
}