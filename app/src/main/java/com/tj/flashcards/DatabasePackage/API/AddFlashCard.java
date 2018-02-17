package com.tj.flashcards.DatabasePackage.API;

import android.os.AsyncTask;

import com.tj.flashcards.DatabasePackage.DatabaseSetup;
import com.tj.flashcards.DatabasePackage.FlashCard;
import com.tj.flashcards.DatabasePackage.Lesson;
import com.tj.flashcards.DatabasePackage.FlashCardDao;

/**
 * Created by TJ on 2/11/2018.
 */

public class AddFlashCard extends AsyncTask<FlashCard, Void, Boolean> {
    final FlashCardDao flashCardDao = DatabaseSetup.getDatabase().flashCardDao();

    @Override
    protected Boolean doInBackground(FlashCard... flashCards) {
        for (int i = 0; i < flashCards.length; i++) {
            flashCardDao.insertFlashCard((com.tj.flashcards.DatabasePackage.FlashCard) flashCards[i]);
        }

        // why is this boolean?
        return Boolean.TRUE;
    }
}

