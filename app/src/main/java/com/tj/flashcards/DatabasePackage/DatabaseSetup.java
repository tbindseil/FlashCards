package com.tj.flashcards.DatabasePackage;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.tj.flashcards.MainActivity;

/**
 * Created by TJ on 2/11/2018.
 */

public class DatabaseSetup {
    private static AppDatabase db = null;

    public static AppDatabase getDatabase() {
        if (db == null) {
            db = Room.databaseBuilder(MainActivity.getAppContext(), AppDatabase.class, MainActivity.DATABASE_NAME).build();
        }
        return db;
    }
}
