package com.tj.flashcards.DatabasePackage.API;

import android.os.AsyncTask;

import com.tj.flashcards.DatabasePackage.DatabaseSetup;
import com.tj.flashcards.DatabasePackage.FlashCard;
import com.tj.flashcards.DatabasePackage.FlashCardDao;

import java.util.List;

/**
 * Created by TJ on 2/17/2018.
 */

public class GetCardsFromLessonID extends AsyncTask<Integer, Void, List<FlashCard>> {
    final private FlashCardDao flashCardDao = DatabaseSetup.getDatabase().flashCardDao();

    @Override
    protected List<FlashCard> doInBackground(Integer... lessonId) {
        if (lessonId[0] == -1) {
            return null;
        } else {
            return flashCardDao.getCardsFromLessonID(lessonId[0]);
        }
    }
}