package com.tj.flashcards.DatabasePackage;

import java.util.List;

/**
 * Created by TJ on 2/3/2018.
 */

@Entity
public class Lesson {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "title");
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
}
