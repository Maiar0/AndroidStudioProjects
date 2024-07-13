package com.hire.dennisward;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtils {

    public static final int PERMISSION_REQUEST_CODE_CALL_PHONE = 1;
    public static final int PERMISSION_REQUEST_CODE_INTERNET = 2;

    // Check if a specific permission is granted
    public static boolean isPermissionGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    // Request a specific permission
    public static void requestPermission(Activity activity, String permission, int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }

    // Check if CALL_PHONE permission is granted
    public static boolean isCallPhonePermissionGranted(Context context) {
        return isPermissionGranted(context, android.Manifest.permission.CALL_PHONE);
    }

    // Check if INTERNET permission is granted
    public static boolean isInternetPermissionGranted(Context context) {
        return isPermissionGranted(context, android.Manifest.permission.INTERNET);
    }

    // Request CALL_PHONE permission
    public static void requestCallPhonePermission(Activity activity) {
        requestPermission(activity, android.Manifest.permission.CALL_PHONE, PERMISSION_REQUEST_CODE_CALL_PHONE);
    }

    // Request INTERNET permission
    public static void requestInternetPermission(Activity activity) {
        requestPermission(activity, android.Manifest.permission.INTERNET, PERMISSION_REQUEST_CODE_INTERNET);
    }
}
