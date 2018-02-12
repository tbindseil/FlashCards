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
        flashCardDao.insertFlashCard((com.tj.flashcards.DatabasePackage.FlashCard) flashCards[0]);
        return Boolean.TRUE;
    }
}

