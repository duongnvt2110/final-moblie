package com.example.duong.myapplication.utils;

import android.util.Log;

import com.example.duong.myapplication.LocationList;
import com.example.duong.myapplication.R;
import com.example.duong.myapplication.ReviewList;

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

public class QueryUtils {
    public final static String LOG_TAG = QueryUtils.class.getSimpleName();

    public static ArrayList<LocationList> fetchLocationDataByPage(int page)
    {
        ArrayList<LocationList> locations = null;
        String requestUrl = "http://206.189.80.94:8000/api/locations/list?page="+page;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url = createURL(requestUrl);
        String jsonReponse = null;
        try {
            jsonReponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        Log.d("JSON: " , jsonReponse);

        try {
            locations = getLocationDataFromJson(jsonReponse);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error getting data from JSON");
        }
        Log.d("Data",locations.toString());
        return locations;
    }


    public static ArrayList<LocationList> fetchLocationByDistanceData(String log, String lat)
    {

        ArrayList<LocationList> locations = null;
        String requestUrl = "http://206.189.80.94:8000/api/locations/api/locations?lng=106.714081&maxDistance=5&lat=10.801440";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url = createURL(requestUrl);
        String jsonReponse = null;
        try {
            jsonReponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        Log.d("JSON: " , jsonReponse);

        try {
            locations = getLocationDataFromJson(jsonReponse);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error getting data from JSON");
        }
        Log.d("Data",locations.toString());
        return locations;
    }


    public static ArrayList<LocationList> fetchLocationData()
    {
        ArrayList<LocationList> locations = null;
        String requestUrl = "http://206.189.80.94:8000/api/locations/list";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url = createURL(requestUrl);
        String jsonReponse = null;
        try {
            jsonReponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        Log.d("JSON: " , jsonReponse);

        try {
            locations = getLocationDataFromJson(jsonReponse);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error getting data from JSON");
        }
        Log.d("Data",locations.toString());
        return locations;
    }

    public static LocationList fetchDetailLocationData(String id)
    {
        LocationList location = null;
        String requestUrl = "http://206.189.80.94:8000/api/locations/"+id;
        Log.d("Url", requestUrl);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url = createURL(requestUrl);
        String jsonReponse = null;
        try {
            jsonReponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        Log.d("JSON: " , jsonReponse);

        try {
            location = getDetailLocation(jsonReponse);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error getting data from JSON");
        }
        Log.d("Data",location.toString());
        return location;
    }

    public static URL createURL(String requestString)
    {
        URL url = null;
        try {
            url = new URL(requestString);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL.", e);
        }
        return url;
    }
    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output =  new StringBuilder();
        if (inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line != null)
            {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonReponse = "";
        if (url == null)
        {
            return jsonReponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200)
            {
                inputStream = urlConnection.getInputStream();
                jsonReponse = readFromStream(inputStream);
            }
            else
            {
                Log.e(LOG_TAG, "Error response code" + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the JSON result", e);
        } finally {
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
            if(inputStream != null)
            {
                inputStream.close();
            }
        }
        return jsonReponse;
    }
    private static ArrayList<LocationList> getLocationDataFromJson(String locationJsonStr)
            throws JSONException {


        final String OWM_ITEMS = "items";
        final String OWM_DATA = "data";

        JSONObject locationJson = new JSONObject(locationJsonStr);
        JSONObject locationData = locationJson.getJSONObject(OWM_DATA);
        JSONArray locationArray = locationData.getJSONArray(OWM_ITEMS);


        ArrayList<LocationList> results = new ArrayList<>();
        for(int i = 0; i < locationArray.length(); i++) {
            JSONObject locationObject = locationArray.getJSONObject(i);
            String id = locationObject.getString("_id");
            String name = locationObject.getString("name");
            Integer rating = locationObject.getInt("rating");
            String address = locationObject.getString("address");


            LocationList location = new LocationList(id, R.drawable.coffee, name, address, rating, 100);


            results.add(location);
        }

        for (LocationList s : results) {
            Log.v(LOG_TAG, "Forecast entry: " + s);
        }
        return results;
    }
    private static LocationList getDetailLocation(String locationJsonStr)
            throws JSONException {


        JSONObject locationObject = new JSONObject(locationJsonStr);
            String id = locationObject.getString("_id");
            String name = locationObject.getString("name");
            Integer rating = locationObject.getInt("rating");
            String address = locationObject.getString("address");
            LocationList location = new LocationList(id, R.drawable.coffee, name, address, rating, 100);
            JSONArray openingTimes = locationObject.getJSONArray("openingTimes");

            String openningTime = "";
            for(int i = 0; i < openingTimes.length(); i++){
                JSONObject openingTimeObject = openingTimes.getJSONObject(i);
                String days = openingTimeObject.getString("days");
                String closing = openingTimeObject.getString("closing");
                String ot = "           " + days + ":  " + "7:00-" + closing;
                openningTime = openningTime + ot + "\n";
            }

            location.setOpeningTime(openningTime);

            ArrayList<ReviewList> reviewLists = new ArrayList<ReviewList>();

            JSONArray reviewArr = locationObject.getJSONArray("reviews");
            for(int i = 0; i < reviewArr.length(); i++){
                JSONObject reviewObj = reviewArr.getJSONObject(i);
                String author = reviewObj.getString("author");
                String reviewId = reviewObj.getString("_id");
                String reviewText = reviewObj.getString("reviewText");
                int reviewRating = reviewObj.getInt("rating");

                ReviewList review = new ReviewList(reviewId, author, reviewText, reviewRating);
                reviewLists.add(review);
            }

            location.setReviews(reviewLists);
        return location;
    }

}
