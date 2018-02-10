package com.tj.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.arch.persistence.room.Room;
import android.widget.RadioButton;

import com.tj.flashcards.DatabasePackage.AppDatabase;
import com.tj.flashcards.DatabasePackage.Lesson;
import com.tj.flashcards.DatabasePackage.LessonDao;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String DATABASE_NAME = "database-name";
    public static final String LESSON_ID_FROM_MAIN_ACTIVITY = "title_id";

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_edit:
                if (checked)
                    // add new button to lesson list
                    break;
            case R.id.radio_practice:
                if (checked)
                    // remove new button from lesson list
                    break;
        }
    }

    private boolean listLessons() {
        // get LessonDAO
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME).build();

        List<Lesson> lessonsToDisplay = db.lessonDao().getAll();

        if (lessonsToDisplay == null) {
            return false;
        }

        Iterator<Lesson> itr = lessonsToDisplay.iterator();
        Lesson curr = null;

        while (itr.hasNext()) {
            // create portal buton
            curr = itr.next();
            Button b = new Button(this);
            b.setText(curr.getTitle());

            b.setOnClickListener(new MenuListener(curr));

            LinearLayout ll = (LinearLayout)findViewById(R.id.buttonLayout);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            ll.addView(b, lp);
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!listLessons()) {
            Button b = new Button(this);
            b.setText("no lessons found, would you like to create one?");


        }
    }


    class MenuListener implements View.OnClickListener {

        private Intent intent;
        private Lesson lesson;

        public void onClick(View view) {
            intent.putExtra(LESSON_ID_FROM_MAIN_ACTIVITY, lesson.getId());
            startActivity(intent);
        }

        MenuListener(Lesson l) {
            intent = new Intent(MainActivity.this, PracticeActivity.class);
            lesson = l;
        }
    }
}