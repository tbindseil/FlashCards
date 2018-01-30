package com.tj.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private void listLessons() {
        Iterator<Lesson> itr = Lesson.getLessons().iterator();
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lesson.makeDummyLesson("dummmmmmy");
        listLessons();
    }


    class MenuListener implements View.OnClickListener {

        private Intent intent;
        private Lesson lesson;

        public void onClick(View view) {
            intent.putExtra("lesson", lesson);
            startActivity(intent);
        }

        MenuListener(Lesson l) {
            intent = new Intent(MainActivity.this, PracticeActivity.class);
            lesson = l;
        }
    }
}