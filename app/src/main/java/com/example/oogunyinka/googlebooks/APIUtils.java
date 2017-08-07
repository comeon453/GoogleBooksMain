package com.example.oogunyinka.googlebooks;

/**
 * Created by oogunyinka on 02/08/2017.
 */

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class with methods to help perform the HTTP request and
 * parse the response.
 */
public final class APIUtils {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = APIUtils.class.getSimpleName();


    private static URL createURL(String urlString) {
        URL url = null;  //createURL has to return a URL object, therefore if urlString is empty we still return something(null)

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL", e);
        }
        return url; //url is either null or the urlString passed in
    }

    //Make http request using the given url and return a string as the response
    private static String makeHttpRequest(URL url) throws IOException {
        //if url is empty we will return an empty string
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null; //this is used to establish a connection to url
        InputStream inputStream = null; //this is to store the input from the urlConnection

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readStreamData(inputStream);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;

    }


    private static String readStreamData(InputStream streamData) throws IOException {
        StringBuilder output = new StringBuilder();
        if (streamData != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(streamData, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();

    }

    public static ArrayList<Books> getStreamData(String requestURL) {
        URL url = createURL(requestURL);
        String jsonResponse = null;
        String searchInput = "Android";
        try {
            jsonResponse = makeHttpRequest(url);


        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        //Create a book object using the fields from JSON response
        ArrayList<Books> books = getBooksFromJson(jsonResponse, searchInput);
        if (books == null) {
            //TO:DO
        }


        return books;


    }

    private static ArrayList<Books> getBooksFromJson(String booksJSON, String searchInput) {
        //an array to store each book item (with its attibutes)
        ArrayList<Books> books = new ArrayList<>();

        //check if the JSON passed back is empty - terminate early and return null
        if (TextUtils.isEmpty(booksJSON)) {
            return null;
        }

        try {
            JSONObject baseObject = new JSONObject(booksJSON);
            JSONArray items = baseObject.getJSONArray("items");



            if (items.length() != 0) {

                //using 'opt' i.e. optString instead of 'get' i.e. getString will return null if the data is empty
                for (int i = 0; i < items.length(); i++) {
                    JSONObject object = items.optJSONObject(i).optJSONObject("volumeInfo");
                    String title = items.optJSONObject(i).optJSONObject("volumeInfo").optString("title");
                    String author = "No author name";
                    if(object.has("authors")) {
                         author = items.getJSONObject(i).getJSONObject("volumeInfo").getJSONArray("authors").getString(0);
                    }
                    //using optString will return null if string is empty
                    String description = items.optJSONObject(i).optJSONObject("volumeInfo").optString ("description");
                    String date = items.optJSONObject(i).optJSONObject("volumeInfo").optString("publishedDate");
                    double rating = items.optJSONObject(i).optJSONObject("volumeInfo").optDouble("averageRating");

                    if(title != null && author != null && description != null && date != null  ) {
                        books.add(new Books(title, author, description, date, rating));
                    }



                }


            }
            return books;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }


}
