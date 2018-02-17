package com.tj.flashcards.DatabasePackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TJ on 2/3/2018.
 */

@Entity
public class Lesson {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @Ignore
    private List<FlashCard> flashCardList;

    public Lesson() {
        flashCardList = new ArrayList<FlashCard>();
    }

    // NOTE: this framework is sensitive to getID vs getId
    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public List<FlashCard> getFlashCardList() {
        return this.flashCardList;
    }

    public void setId(int id) { this.id = id; }

    public void setTitle(String title) {
        this.title = title;
    }
}
