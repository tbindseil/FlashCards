package com.tj.flashcards.DatabasePackage.API;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tj.flashcards.DatabasePackage.DatabaseSetup;
import com.tj.flashcards.DatabasePackage.Lesson;
import com.tj.flashcards.DatabasePackage.LessonDao;
import com.tj.flashcards.MainActivity;
import com.tj.flashcards.R;

import java.util.Iterator;
import java.util.List;

/**
 * Created by TJ on 2/11/2018.
 */

public class GetAllLessons extends AsyncTask<Void, Void, List<Lesson>> {
    final private LessonDao lessonDao = DatabaseSetup.getDatabase().lessonDao();

    @Override
    protected List<Lesson> doInBackground(Void... voids) {
        return lessonDao.getAll();
    }
}
