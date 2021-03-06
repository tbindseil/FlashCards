package com.tj.flashcards.DatabasePackage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by TJ on 2/3/2018.
 */

@Dao
public interface LessonDao {
    @Query("SELECT * FROM lesson")
    List<Lesson> getAll();

    @Query("SELECT * FROM lesson WHERE id = (:id)")
    Lesson getLesson(int id);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    Long[] insertLesson(Lesson[] lessons);

    @Delete
    void deleteLesson(Lesson lesson);

    @Update
    void updateLesson(Lesson lesson);

    @Query("DELETE FROM lesson")
    void nukeTable();
}
