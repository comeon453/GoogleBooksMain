package com.example.oogunyinka.googlebooks;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by oogunyinka on 02/08/2017.
 */

public class Books  {

    private String mTitle, mAuthor, mDescription;
    private String mDate;
    private double mRating;

    public Books(String title, String author, String description, String date, double rating){
        mTitle = title;
        mAuthor = author;
        mDescription = description;
        mDate = date;
        mRating = rating;

    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmDate() {
        return mDate;
    }

    public double getmRating() {
        return mRating;
    }
}
