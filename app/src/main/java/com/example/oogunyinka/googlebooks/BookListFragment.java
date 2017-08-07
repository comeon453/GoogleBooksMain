package com.example.oogunyinka.googlebooks;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //BookListFragment.//OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookListFragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookListFragment extends Fragment {

    public BookListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.book_list, container, false);
        ArrayList<Books> books = new ArrayList<>();

return rootView;
    }

}