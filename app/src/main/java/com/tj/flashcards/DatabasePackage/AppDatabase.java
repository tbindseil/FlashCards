package com.tj.flashcards.DatabasePackage;

/**
 * Created by TJ on 2/3/2018.
 */

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FlashCardDao flashCardDao();
}
