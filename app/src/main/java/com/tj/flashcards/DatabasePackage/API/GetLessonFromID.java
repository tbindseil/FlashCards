package com.tj.flashcards.DatabasePackage.API;

import android.os.AsyncTask;

import com.tj.flashcards.DatabasePackage.DatabaseSetup;
import com.tj.flashcards.DatabasePackage.Lesson;
import com.tj.flashcards.DatabasePackage.LessonDao;

/**
 * Created by TJ on 2/11/2018.
 */

public class GetLessonFromID extends AsyncTask<Integer, Void, Lesson> {
    final private LessonDao lessonDao = DatabaseSetup.getDatabase().lessonDao();

    @Override
    protected Lesson doInBackground(Integer... lessonId) {
        if (lessonId[0] == -1) {
            return null;
        } else {
            return lessonDao.getLesson(lessonId[0]);
        }
    }
}
