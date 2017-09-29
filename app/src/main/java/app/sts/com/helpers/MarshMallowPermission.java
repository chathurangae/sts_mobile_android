package app.sts.com.helpers;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class MarshMallowPermission {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private Activity activity;

    public MarshMallowPermission(Activity activity) {
        this.activity = activity;
    }

    public void checkRuntimePermissions() {
        if (!checkPermission()) {
            requestPermission();
        }
    }

    private boolean checkPermission() {
        int fineLocationPermissionResult = ContextCompat.checkSelfPermission(activity,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        int coarseLocationPermissionResult = ContextCompat.checkSelfPermission(activity,
                android.Manifest.permission.ACCESS_COARSE_LOCATION);
        return coarseLocationPermissionResult == PackageManager.PERMISSION_GRANTED &&
                fineLocationPermissionResult == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(activity, new String[]
                {
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                }, PERMISSION_REQUEST_CODE);
    }
}
