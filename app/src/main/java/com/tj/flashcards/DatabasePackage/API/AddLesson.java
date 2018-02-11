package com.tj.flashcards.DatabasePackage.API;

import android.os.AsyncTask;

import com.tj.flashcards.DatabasePackage.DatabaseSetup;
import com.tj.flashcards.DatabasePackage.Lesson;
import com.tj.flashcards.DatabasePackage.LessonDao;

import javax.xml.transform.Result;

/**
 * Created by TJ on 2/11/2018.
 */

public class AddLesson extends AsyncTask<Lesson, Void, Boolean> {
    final LessonDao lessonDao = DatabaseSetup.getDatabase().lessonDao();

    @Override
    protected Boolean doInBackground(Lesson... lesson) {
        lessonDao.insertLesson((com.tj.flashcards.DatabasePackage.Lesson) lesson[0]);
        return Boolean.TRUE;
    }
}
