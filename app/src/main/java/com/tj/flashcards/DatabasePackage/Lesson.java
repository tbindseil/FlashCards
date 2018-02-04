package com.tj.flashcards.DatabasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

/**
 * Created by TJ on 2/3/2018.
 */

@Entity
public class Lesson {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    private List<FlashCard> flashCardList;

    public int getID() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public List<FlashCard> getFlashCardList() {
        return this.flashCardList;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
