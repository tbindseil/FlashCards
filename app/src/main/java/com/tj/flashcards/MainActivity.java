package com.tj.flashcards;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tj.flashcards.DatabasePackage.Lesson;
import com.tj.flashcards.DatabasePackage.API.GetAllLessons;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String DATABASE_NAME = "database-name";
    public static final String LESSON_ID_FROM_MAIN_ACTIVITY = "title_id";

    private static Context applicationContext;

    private static Button createButton = null;

    public static Context getAppContext() {
        return applicationContext;
    }

    public void onRadioButtonClicked(View view) {
        // ensure createButton initialized before editting it
        if (createButton == null) {
            return;
        }

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_edit:
                if (checked) {
                    createButton.setVisibility(View.VISIBLE);
                    break;
                }
            case R.id.radio_practice:
                if (checked) {
                    createButton.setVisibility(View.INVISIBLE);
                    break;
                }
        }
    }


    private boolean listLessons(List<Lesson> lessonsToDisplay) {

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

            LinearLayout ll = (LinearLayout) findViewById(R.id.buttonLayout);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            ll.addView(b, lp);
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // allow access to ApplicationContext for databasse
        applicationContext = getApplicationContext();

        try {
            List<Lesson> lessons = new GetAllLessons().execute().get();
            listLessons(lessons);
        } catch (Exception e) {
            Toast.makeText(this, "I tried...", Toast.LENGTH_LONG).show();
        }

        // hide create button
        createButton = new Button(this);
        createButton.setText("Create Lesson");
        createButton.setOnClickListener(new MenuListener(null));
        createButton.setVisibility(View.INVISIBLE);
        LinearLayout ll = (LinearLayout)findViewById(R.id.buttonLayout);
        ll.addView(createButton);
    }

    class MenuListener implements View.OnClickListener {

        private Intent intent;
        private Lesson lesson;

        public void onClick(View view) {
            // choose edit or practice activity based on radio button
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.radio_edit:
                    intent = new Intent(MainActivity.this, EditActivity.class);
                    break;
                case R.id.radio_practice:
                    intent = new Intent(MainActivity.this, PracticeActivity.class);
                    break;
            }

            if (lesson == null) {
                intent.putExtra(LESSON_ID_FROM_MAIN_ACTIVITY, -1);
            } else {
                intent.putExtra(LESSON_ID_FROM_MAIN_ACTIVITY, lesson.getId());
            }
            startActivity(intent);
        }

        MenuListener(Lesson l) {
            lesson = l;
        }
    }
}