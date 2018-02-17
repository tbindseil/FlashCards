package com.tj.flashcards.DatabasePackage.API;

import android.os.AsyncTask;

import com.tj.flashcards.DatabasePackage.DatabaseSetup;
import com.tj.flashcards.DatabasePackage.FlashCard;
import com.tj.flashcards.DatabasePackage.FlashCardDao;

import java.util.List;

/**
 * Created by TJ on 2/17/2018.
 */

public class UpdateFlashCard extends AsyncTask<FlashCard, Void, Void> {
    final private FlashCardDao flashCardDao = DatabaseSetup.getDatabase().flashCardDao();

    @Override
    protected Void doInBackground(FlashCard... cards) {
        for (int i = 0; i < cards.length; i++) {
            flashCardDao.updateFlashCard(cards[i]);
        }
        return null;
    }
}