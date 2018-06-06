package app.nytimes.promobi.com.nytimesapp.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static final String BASE_URL = "https://api.nytimes.com/svc/";
    public static final String TOP_STORIES_V2_URL = "topstories/v2/";
    public static final String HOME_JSON = "home.json";
    public static final String API_KEY = "46af3805d6ab40e2b489a59f7ce046f1";
    public static final String HOME = "Home";

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    public static String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static boolean checkInternetConenction(Activity activity) {
        // get Connectivity Manager object to check connection
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(activity.getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        assert connectivityManager != null;
        if (connectivityManager.getNetworkInfo(0).getState() ==
                android.net.NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connectivityManager.getNetworkInfo(1).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;
        } else if (
                connectivityManager.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.DISCONNECTED ||
                        connectivityManager.getNetworkInfo(1).getState() ==
                                android.net.NetworkInfo.State.DISCONNECTED) {
            return false;
        }
        return false;
    }


    public static void displayDialog(Activity activity) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.setTitle("Please check your internet connection and try again...");
        dialog.setCancelable(true);
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}
