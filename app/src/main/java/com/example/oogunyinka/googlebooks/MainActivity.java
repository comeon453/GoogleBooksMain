package com.example.oogunyinka.googlebooks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private static final String GGBooks_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=30";
    public static final String LOG_TAG = APIUtils.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);
        bookSearchAsyncTask task = new bookSearchAsyncTask();
        task.execute(GGBooks_REQUEST_URL);
    }




    private void updateUi(ArrayList book) {

        BooksAdapter adapter = new BooksAdapter(this, book);
        ListView listView = (ListView) findViewById(R.id.list);


        listView.setAdapter(adapter);


    }

    private class bookSearchAsyncTask extends AsyncTask<String, Void, ArrayList>{


        @Override
        protected ArrayList<Books> doInBackground(String... urls) {

            //return null if the url is null or empty
            if (urls.length < 1 || urls[0] == null){
                return null;
            }
            ArrayList<Books> book = APIUtils.getStreamData(urls[0]);

            return book;
        }

        @Override
        protected void onPostExecute( ArrayList book){
            if (book == null){

                return;
            }
            updateUi(book);

        }
    }
}
