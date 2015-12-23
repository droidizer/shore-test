package termine24.secondinterviewtest.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by shanemurphy on 23/12/2015.
 * Copyright (c) 2015 Shore. All rights reserved.
 */
public class Foreground implements Application.ActivityLifecycleCallbacks {

    public interface ForegroundListener {
        void applicationCameToForeground();
        void applicationWentToBackground();
    }

    private static Foreground instance;

    private List<ForegroundListener> listeners = new CopyOnWriteArrayList<>();
    private boolean inForeground = true;
    private int resumed = 0;
    private int paused = 0;

    public static void init(Application app){
        if (instance == null){
            instance = new Foreground();
            app.registerActivityLifecycleCallbacks(instance);
        }
    }

    public static Foreground getInstance(){
        return instance;
    }

    private void foregroundOrBackground() {
        if(paused >= resumed && inForeground) {
            inForeground = false;
            for (ForegroundListener l : listeners){
                try {
                    l.applicationWentToBackground();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        } else if(resumed > paused && !inForeground) {
            inForeground = true;
            for (ForegroundListener l : listeners){
                try {
                    l.applicationCameToForeground();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        }
    }

    private void delayedCallToForegroundOrBackground() {
        //This is delayed so that activity onCreate and onFinish can complete
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                foregroundOrBackground();
            }
        }, 300);
    }

    public void addListener(ForegroundListener listener){
        listeners.add(listener);
    }

    public void removeListener(ForegroundListener listener){
        listeners.remove(listener);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity){
        ++paused;
        // Don't check for foreground or background right away
        // finishing an activity and starting a new one will trigger to many
        // foreground <---> background switches
        //
        // In half a second call foregroundOrBackground
        delayedCallToForegroundOrBackground();
    }

    @Override
    public void onActivityResumed(Activity activity){
        ++resumed;
        // Don't check for foreground or background right away
        // finishing an activity and starting a new one will trigger to many
        // foreground <---> background switches
        //
        // In half a second call foregroundOrBackground
        delayedCallToForegroundOrBackground();
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
