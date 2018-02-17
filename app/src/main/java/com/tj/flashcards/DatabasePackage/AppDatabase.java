package com.tj.flashcards.DatabasePackage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;


/**
 * Created by TJ on 2/3/2018.
 */

// TODO magic numbers?
@Database(entities = {FlashCard.class, Lesson.class}, version = 3, exportSchema = false) // {FlashCard.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FlashCardDao flashCardDao();
    public abstract LessonDao lessonDao();
}
