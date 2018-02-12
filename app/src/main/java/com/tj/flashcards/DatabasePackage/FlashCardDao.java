package com.tj.flashcards.DatabasePackage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by TJ on 2/3/2018.
 */

@Dao
public interface FlashCardDao {
    @Query("SELECT * FROM flashcard")
    List<FlashCard> getAll();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertFlashCard(FlashCard flashCard);
}
