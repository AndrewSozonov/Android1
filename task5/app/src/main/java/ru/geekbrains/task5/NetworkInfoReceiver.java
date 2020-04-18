package ru.geekbrains.task5;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class NetworkInfoReceiver extends BroadcastReceiver {


    private static final int NOTIFY_ID = 101;
    private static String CHANNEL_ID = "Network channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        String message;


        if (checkInternet(context)) {
            message = "Network Available";

        } else {
            message = "Network Not Available";

        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.icon511)
                .setContentTitle("NetworkInfoReceiver")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    boolean checkInternet (Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
}
