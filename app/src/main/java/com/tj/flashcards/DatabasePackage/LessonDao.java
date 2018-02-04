package com.tj.flashcards.DatabasePackage;

import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by TJ on 2/3/2018.
 */

public interface LessonDao {
    @Query("SELECT * FROM lesson")
    List<Lesson> getAll();
}
