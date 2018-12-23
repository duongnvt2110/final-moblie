package com.example.duong.myapplication.utils;

import android.util.Log;

import com.example.duong.myapplication.LocationList;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostUtils {
    public final static String LOG_TAG = PostUtils.class.getSimpleName();
    protected String simpleFileName = "token.txt";


    public static int addReview(String locationId, int rating, String reviewText)
    {
        JSONObject data = new JSONObject();
        String token = null;

        try {
            data.put("rating", rating);
            data.put("reviewText", reviewText);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String requestUrl = "http://206.189.80.94:8000/api/'/locations/" + locationId + "/reviews'";
        Log.d("Url", requestUrl);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url = QueryUtils.createURL(requestUrl);
        String jsonReponse = null;

        try {
            jsonReponse = makePostHttpRequest(url, data);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
            return 0;
        }

        if(jsonReponse.equals("0")){
            return 0;
        }
        Log.d("JSON: " , jsonReponse);

        try {
            JSONObject json = new JSONObject(jsonReponse);
            token = json.getString("token");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 1;

    }

    public static String login(String username, String password)
    {
        JSONObject data = new JSONObject();
        String token = null;

        try {
            data.put("email", username);
            data.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String requestUrl = "http://206.189.80.94:8000/api/authentication/login";
        Log.d("Url", requestUrl);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url = QueryUtils.createURL(requestUrl);
        String jsonReponse = null;

        try {
            jsonReponse = makePostHttpRequest(url, data);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
            return "0";
        }

        if(jsonReponse.equals("0")){
            return "0";
        }
        Log.d("JSON: " , jsonReponse);

        try {
            JSONObject json = new JSONObject(jsonReponse);
            token = json.getString("token");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return token;

    }

    public static String register(String email, String name, String password)
    {
        JSONObject data = new JSONObject();
        String token = null;

        try {
            data.put("email", email);
            data.put("name", name);
            data.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String requestUrl = "http://206.189.80.94:8000/api/authentication/register";
        Log.d("Url", requestUrl);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url = QueryUtils.createURL(requestUrl);
        String jsonReponse = null;

        try {
            jsonReponse = makePostHttpRequest(url, data);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
            return "0";
        }

        if(jsonReponse.equals("0")){
            return "0";
        }
        Log.d("JSON: " , jsonReponse);

        try {
            JSONObject json = new JSONObject(jsonReponse);
            token = json.getString("token");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return token;

    }


    public static void addReview(JSONObject data)
    {
        LocationList location = null;
        String requestUrl = "http://206.189.80.94:8000/api/locations/";
        Log.d("Url", requestUrl);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        URL url = QueryUtils.createURL(requestUrl);
        String jsonReponse = null;

        try {
            jsonReponse = makePostHttpRequest(url, data);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        Log.d("JSON: " , jsonReponse);

        Log.d("Data",location.toString());
    }


    private static String makePostHttpRequest(URL url, JSONObject data) throws IOException {
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
            urlConnection.setRequestMethod("POST");
            urlConnection.getDoInput();
            urlConnection.getDoOutput();
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.connect();
            String dataString =  data.toString();
            byte[] outputInBytes = dataString.getBytes("UTF-8");
            OutputStream os = urlConnection.getOutputStream();
            os.write( outputInBytes );
            os.close();

            if (urlConnection.getResponseCode() == 200)
            {
                inputStream = urlConnection.getInputStream();
                jsonReponse = QueryUtils.readFromStream(inputStream);
            }
            else
            {
                Log.e(LOG_TAG, "Error response code" + urlConnection.getResponseCode());
                jsonReponse = "0";
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

}
