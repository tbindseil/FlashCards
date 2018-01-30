package com.tj.flashcards;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by TJ on 1/28/2018.
 */

public class FlashCard implements Parcelable {
    private String front;
    private String back;

    public FlashCard(String front, String back) {
        this.front = front;
        this.back = back;
    }

    @Override
    public int describeContents() {
        // return 1 for file descriptor??
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(front);
        dest.writeString(back);
    }

    public static final Parcelable.Creator<FlashCard> CREATOR
            = new Parcelable.Creator<FlashCard>() {
        public FlashCard createFromParcel(Parcel in) {
            return new FlashCard(in);
        }

        public FlashCard[] newArray(int size) {
            return new FlashCard[size];
        }
    };

    private FlashCard(Parcel in) {
        this.front = in.readString();
        this.back = in.readString();
    }
}
