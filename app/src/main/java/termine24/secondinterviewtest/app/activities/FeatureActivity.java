package termine24.secondinterviewtest.app.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;

import java.io.IOException;

import termine24.secondinterviewtest.R;
import termine24.secondinterviewtest.app.activities.adapter.ContactAdapter;
import termine24.secondinterviewtest.app.model.Contacts;
import termine24.secondinterviewtest.app.network.ShoreRequestQueue;
import termine24.secondinterviewtest.app.network.requests.FooRequest;

/**
 * Created by shanemurphy on 22/12/2015.
 * Copyright (c) 2015 Shore. All rights reserved.
 */
public class FeatureActivity extends BaseActivity {

    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout contentView = (RelativeLayout) findViewById(R.id.content);
        View featureContent = getLayoutInflater().inflate(R.layout.content_feature, contentView);

        //setup listview
        ListView listView = (ListView) featureContent.findViewById(R.id.listView);
        adapter = new ContactAdapter(this);
        listView.setAdapter(adapter);

        sendFooRequest();
    }



    private void sendFooRequest() {
        //show loading indicator
        showLoadingIndicator();
        //create request
        FooRequest fooRequest = new FooRequest(fooSuccessListener, fooFailureListener);
        //add request to the queue
        ShoreRequestQueue.getInstance(this).getRequestQueue().add(fooRequest);
    }


    private final Response.Listener<JSONArray> fooSuccessListener = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            //hide loading indicator
            hideLoadingIndicator();
            //parse the data
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Contacts contacts = objectMapper.readValue(response.toString(), Contacts.class);
                //set the data to the adapter
                adapter.setContacts(contacts);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                //failed to parse the json data show error message
                showErrorDialog(ioException.getLocalizedMessage());
            }
        }
    };


    private final Response.ErrorListener fooFailureListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            //hide loading indicator
            hideLoadingIndicator();
            //show error dialog
            showErrorDialog(error.getLocalizedMessage());
        }
    };


}
