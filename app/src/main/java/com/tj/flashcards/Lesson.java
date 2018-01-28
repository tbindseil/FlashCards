package com.tj.flashcards;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by TJ on 1/28/2018.
 */

public class Lesson {
    // static list of all lessons
    private static List<Lesson> lessons = new ArrayList<Lesson>();

    private static void initLessons() {
        lessons = new ArrayList<Lesson>();
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
}
