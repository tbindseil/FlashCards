package com.tj.flashcards.DatabasePackage;

/**
 * Created by TJ on 2/3/2018.
 */

@Entity
public class FlashCard {
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "front");
    private String front;

    @ColumnInfo(name = "back");
    private String back;

    @ColumnInfo(name = "lesson");
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
}
