package at.ac.htl;

import android.app.Application;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();

    @Inject
    public MyApplication() {
        Log.i(TAG, "Started");
    }
}
