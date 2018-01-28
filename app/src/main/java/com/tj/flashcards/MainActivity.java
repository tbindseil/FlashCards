package com.tj.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private void listLessons() {
        for (final Lesson l : Lesson.getLessons()) {
            // create portal buton
            Button b = new Button(this);
            b.setText(l.getTitle());
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Practice.class);
                    startActivity(intent);
                }
            });

            LinearLayout ll = (LinearLayout)findViewById(R.id.buttonLayout);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            ll.addView(b, lp);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lesson dummyLesson = new Lesson("dummy");
        listLessons();
    }
}