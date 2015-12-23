package termine24.secondinterviewtest.app.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by shanemurphy on 22/12/2015.
 * Copyright (c) 2015 Shore. All rights reserved.
 */
public class ShoreRequestQueue {

    private static ShoreRequestQueue instance;
    private static Context ctx;

    private RequestQueue mRequestQueue;

    private ShoreRequestQueue(Context context) {
        ctx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized ShoreRequestQueue getInstance(Context context) {
        if (instance == null) {
            instance = new ShoreRequestQueue(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        getRequestQueue().add(request);
    }


    public void cancelPendingRequests(String tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
