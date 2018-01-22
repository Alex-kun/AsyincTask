package com.example.alejandroquiros.androidjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public TextView mTxtDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtDisplay = (TextView) findViewById(R.id.jsonText);


        //ImageView mImageView;
        //mImageView = findViewById(R.id.imageView);

        HttpAsyncTask httpAsyncTask = new HttpAsyncTask(this);
        httpAsyncTask.execute("https://vignette.wikia.nocookie.net/es.pokemon/images/a/ae/Mudkip.png/revision/latest?cb=20140612152830");
        readJson();


    }

    public void readJson(){
        final TextView mTxtDisplay;

        String url = "http://ultraumbral.com/jsonExampleAndroid/json.php";
        //String url = "http://my-json-feed";


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //mTxtDisplay.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        //mTxtDisplay.setText("Se ha ejecutado el metodo de Error");

                    }
                });


// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }
}
