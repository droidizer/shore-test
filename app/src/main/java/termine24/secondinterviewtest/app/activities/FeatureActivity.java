package termine24.secondinterviewtest.app.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import termine24.secondinterviewtest.R;
import termine24.secondinterviewtest.app.network.ShoreRequestQueue;
import termine24.secondinterviewtest.app.network.requests.FooRequest;

/**
 * Created by shanemurphy on 22/12/2015.
 * Copyright (c) 2015 Shore. All rights reserved.
 */
public class FeatureActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentView = (FrameLayout) findViewById(R.id.content);
        getLayoutInflater().inflate(R.layout.content_feature, contentView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        sendFooRequest();
    }



    private void sendFooRequest() {
        FooRequest fooRequest = new FooRequest(fooSuccessListener, fooFailureListener);
        ShoreRequestQueue.getInstance(this).getRequestQueue().add(fooRequest);
    }


    private final Response.Listener<JSONArray> fooSuccessListener = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            Log.e("test:", "foo request data: " + response.toString());
        }
    };


    private final Response.ErrorListener fooFailureListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("test:", "error: " + error.toString());
            }
    };


}
