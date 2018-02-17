package com.tj.flashcards.DatabasePackage.API;

import android.os.AsyncTask;

import com.tj.flashcards.DatabasePackage.DatabaseSetup;
import com.tj.flashcards.DatabasePackage.Lesson;
import com.tj.flashcards.DatabasePackage.LessonDao;

/**
 * Created by TJ on 2/17/2018.
 */

public class NukeTable extends AsyncTask<Void, Void, Void> {
    final LessonDao lessonDao = DatabaseSetup.getDatabase().lessonDao();

    @Override
    protected Void doInBackground(Void... lesson) {
        lessonDao.nukeTable();
        return null;
    }
}
