package termine24.secondinterviewtest.app.network.requests;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

/**
 * Created by shanemurphy on 22/12/2015.
 * Copyright (c) 2015 Shore. All rights reserved.
 */
public class FooRequest extends JsonArrayRequest {

    private static final String url = "http://www.json-generator.com/api/json/get/cueUxnWiGa?indent=2";

    public FooRequest(Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }
}
