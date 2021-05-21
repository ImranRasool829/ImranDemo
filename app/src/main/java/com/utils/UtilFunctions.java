package com.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.myapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static androidx.core.app.ActivityCompat.finishAffinity;

;

public class UtilFunctions {
    public static void showSnackBar(Context context, String message, View view) {
        if (message != null)
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).setActionTextColor(ContextCompat.getColor(context, R.color.white)).show();
    }


    public static void showToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    // if (isInputTypeValid(name) && isInputTypeValid(email) && isInputTypeValid(password))
    private boolean isInputTypeValid(EditText editText) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (editText.getText().toString().trim().length() > 0) {
            return true; // returs true if field is not empty
        }
        editText.setError("Please Fill This");
        editText.requestFocus();
        return false;
    }

    public static boolean checkMyPermission(Context mContext) {

        int permissionCheckw = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCheckr = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionChecka = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.READ_SMS);
        int permissionCheckb = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.RECEIVE_SMS);
        int permissionChecCall = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.CALL_PHONE);
        int permissionChecExternal = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionChecInternal = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionChecCamera = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.CAMERA);
        return permissionCheckw == 0 && permissionCheckr == 0 &&
                permissionChecka == 0 && permissionCheckb == 0 && permissionChecCall == 0 &&
                permissionChecExternal == 0 && permissionChecInternal == 0 && permissionChecCamera == 0;
    }

    public static void permissions(Context mContext) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (!Settings.System.canWrite(mContext)) {
                ((Activity) mContext).requestPermissions(new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,}, 4);
            }

        }
    }

    public static File getListFiles(File dir, String name) {
        File[] children = dir.listFiles();

        if (children == null)
            return null;
        for (File child : children) {
            if (child.isDirectory()) {
                File found = getListFiles(child, name);
                if (found != null) return found;
            } else {
                if (name.equals(child.getName())) return child;
            }
        }

        return null;
    }

    public static void exitAppCLICK(Activity activity) {

        finishAffinity(activity);
        System.exit(0);

    }

    //delete mobile cache
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    public static void open(Activity activity, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
