package com.example.oogunyinka.googlebooks;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by oogunyinka on 02/08/2017.
 */

public class BooksAdapter extends ArrayAdapter<Books> {

    public BooksAdapter(Activity context, ArrayList<Books>books){

        super(context, 0, books);
    }



    public View getView(int position, View convertView, ViewGroup parent){
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_item, parent, false);
        }

        Books currentBook = getItem(position);
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        titleView.setText(currentBook.getmTitle());

        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        authorView.setText(currentBook.getmAuthor());

        TextView descriptionView = (TextView) listItemView.findViewById(R.id.description);
        descriptionView.setText(currentBook.getmDescription());

        TextView dateView = (TextView) listItemView.findViewById(R.id.Date);
        dateView.setText(currentBook.getmDate());

        TextView ratingView = (TextView) listItemView.findViewById(R.id.rating);
     //   ratingView.setText((int) currentBook.getmRating());

      return listItemView;

    }
}




