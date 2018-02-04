package com.tj.flashcards.DatabasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by TJ on 2/3/2018.
 */

@Entity
public class FlashCard {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "front")
    private String front;

    @ColumnInfo(name = "back")
    private String back;

    // TODO account for one flashcard being a part of multiple lessons
    @ColumnInfo(name = "lesson")
    private int associatedLessonID;

    public int getID() {
        return this.id;
    }

    public String getFront() {
        return this.front;
    }

    public String getBack() {
        return this.back;
    }

    public int getAssociatedLessonID() {
        return this.associatedLessonID;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public void setAssociatedLessonID(int associatedLessonID) {
        this.associatedLessonID = associatedLessonID;
    }
}
