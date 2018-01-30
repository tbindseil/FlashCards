package com.tj.flashcards;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by TJ on 1/28/2018.
 */

public class Lesson implements Parcelable {
    // static list of all lessons
    private static List<Lesson> lessons = new ArrayList<Lesson>();

    private static void initLessons() {
        lessons = new ArrayList<Lesson>();
    }

    public static void makeDummyLesson(String title) {
        Lesson l = new Lesson(title);
    }

    public static List<Lesson> getLessons() {
        if (lessons == null) {
            initLessons();
        }
        return lessons;
    }

    // fields
    private String title;
    private List<FlashCard> flashCards;

    public Lesson(String title) {
        this.title = title;
        flashCards = new ArrayList<FlashCard>();
        flashCards.add(new FlashCard("front1", "back1"));
        flashCards.add(new FlashCard("front2", "back2"));
        flashCards.add(new FlashCard("front3", "back3"));
        lessons.add(this);
    }

    public String getTitle() {
        return this.title;
    }

    public List<FlashCard> getFlashCards() {
        return flashCards;
    }

    @Override
    public int describeContents() {
        // return 1 for file descriptor??
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeTypedList(flashCards);
    }

    public static final Parcelable.Creator<Lesson> CREATOR
            = new Parcelable.Creator<Lesson>() {
        public Lesson createFromParcel(Parcel in) {
            return new Lesson(in);
        }

        public Lesson[] newArray(int size) {
            return new Lesson[size];
        }
    };

    private Lesson(Parcel in) {
        this.title = in.readString();
        in.readTypedList(this.flashCards, FlashCard.CREATOR);
    }
}
