package com.tj.flashcards.DatabasePackage.API;

import android.os.AsyncTask;

import com.tj.flashcards.DatabasePackage.DatabaseSetup;
import com.tj.flashcards.DatabasePackage.FlashCard;
import com.tj.flashcards.DatabasePackage.FlashCardDao;
import com.tj.flashcards.DatabasePackage.Lesson;
import com.tj.flashcards.DatabasePackage.LessonDao;

/**
 * Created by TJ on 2/17/2018.
 */

public class DeleteFlashCard extends AsyncTask<FlashCard, Void, Void> {
    final private FlashCardDao flashCardDao = DatabaseSetup.getDatabase().flashCardDao();

    @Override
    protected Void doInBackground(FlashCard... flashCards) {
        flashCardDao.deleteFlashCard(flashCards[0]);
        return null;
    }
}
