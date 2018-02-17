package com.tj.flashcards.DatabasePackage.API;

import android.os.AsyncTask;

import com.tj.flashcards.DatabasePackage.DatabaseSetup;
import com.tj.flashcards.DatabasePackage.Lesson;
import com.tj.flashcards.DatabasePackage.LessonDao;

/**
 * Created by TJ on 2/17/2018.
 */

public class DeleteLesson extends AsyncTask<Lesson, Void, Void> {
    final private LessonDao lessonDao = DatabaseSetup.getDatabase().lessonDao();

    @Override
    protected Void doInBackground(Lesson... lessons) {
        for (int i = 0; i < lessons.length; i++) {
            lessonDao.deleteLesson(lessons[i]);
        }
        return null;
    }
}
