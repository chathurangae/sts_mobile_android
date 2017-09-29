package app.sts.com.helpers;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

public class AppController extends Application {

    private static AppController mInstance;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FlowManager.init(this);
    }
}
