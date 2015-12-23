package termine24.secondinterviewtest.app;

import android.app.Application;

import termine24.secondinterviewtest.app.network.ShoreRequestQueue;


/**
 * Created by shanemurphy on 22/12/2015.
 * Copyright (c) 2015 Shore. All rights reserved.
 */
public class ShoreTestApplication extends Application implements Foreground.ForegroundListener {

    @Override
    public void onCreate() {
        super.onCreate();

        //used to determine if the application is in the background or foreground
        Foreground.init(this);
        Foreground.getInstance().addListener(this);

        //Init singleton request queue
        ShoreRequestQueue.getInstance(this);
        //Init singleton data model
        DataModel.getInstance(this);
    }


    @Override
    public void applicationCameToForeground() {

    }

    @Override
    public void applicationWentToBackground() {

    }
}
