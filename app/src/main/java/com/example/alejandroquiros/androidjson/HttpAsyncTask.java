package com.example.alejandroquiros.androidjson;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by alejandroquiros on 22/1/18.
 */

public class HttpAsyncTask  extends AsyncTask<String, Integer, String>{
    public String jsonString;
    public MainActivity mainActivity;
    JSONObject json;



    public HttpAsyncTask(MainActivity mainActivity){
        this.mainActivity=mainActivity;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... urls) {



        String url = "http://ultraumbral.com/jsonExampleAndroid/json.php";



        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        jsonString = response.toString();
                        json = response;
                        mainActivity.mTxtDisplay.setText(jsonString);
                        Log.v("HttpAsyncTask", "JSON "+jsonString);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        mainActivity.mTxtDisplay.setText("Se ha ejecutado el metodo de Error");
                        Log.v("HttpAsyncTask", "ERROR");

                    }
                });


        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(mainActivity).addToRequestQueue(jsObjRequest);

        return jsonString;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.v("HttpAsyncTask", "PORCENTAJE DE PROGRESO: "+values[0]);
    }

    @Override
    protected void onPostExecute(String in) {
        super.onPostExecute(in);
        Log.v("HttpAsyncTask", "PROCESO TERMINADO: "+in);
        Log.v("HttpAsyncTask", "JSON: "+json);
        try {
            Log.v("json", json.getJSONObject("user1").get("nombre").toString());
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
