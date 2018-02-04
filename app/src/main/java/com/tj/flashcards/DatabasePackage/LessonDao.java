package com.tj.flashcards.DatabasePackage;

import java.util.List;

/**
 * Created by TJ on 2/3/2018.
 */

public interface LessonDao {
    @Query("SELECT * FROM lesson")
    List<Lesson> getAll();
}
