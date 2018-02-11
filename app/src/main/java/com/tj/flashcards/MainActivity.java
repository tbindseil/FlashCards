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

import com.tj.flashcards.DatabasePackage.DatabaseSetup;
import com.tj.flashcards.DatabasePackage.Lesson;
import com.tj.flashcards.DatabasePackage.LessonDao;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String DATABASE_NAME = "database-name";
    public static final String LESSON_ID_FROM_MAIN_ACTIVITY = "title_id";

    private static Context applicationContext;

    public static Context getAppContext() {
        return applicationContext;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        Button b = (Button) findViewById(R.id.create_botton);

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_edit:
                if (checked) {
                    b.setVisibility(View.VISIBLE);
                    break;
                }
            case R.id.radio_practice:
                if (checked) {
                    b.setVisibility(View.INVISIBLE);
                    break;
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // allow access to ApplicationContext for databasse
        applicationContext = getApplicationContext();

        // hide create button
        Button b = (Button) findViewById(R.id.create_botton);
        b.setText("Create Lesson");
        b.setOnClickListener(new MenuListener(null));
        b.setVisibility(View.INVISIBLE);

        new GetAllLessons().execute();
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

    class GetAllLessons extends AsyncTask<Void, Void, List<Lesson>> {
        final LessonDao lessonDao = DatabaseSetup.getDatabase().lessonDao();

        @Override
        protected List<Lesson> doInBackground(Void... voids) {
            return lessonDao.getAll();
        }

        protected void onPostExecute(List<Lesson> lessons) {
            Iterator<Lesson> itr = lessons.iterator();
            Lesson curr = null;

            while (itr.hasNext()) {
                // create portal buton
                curr = itr.next();
                Button b = new Button(MainActivity.this);
                b.setText(curr.getTitle());

                b.setOnClickListener(new MenuListener(curr));

                LinearLayout ll = (LinearLayout)findViewById(R.id.buttonLayout);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                ll.addView(b, lp);
            }

        }
    }

}