package com.example.nick.somethingapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showUnclearNotification();
        finish();

    }


    private void showUnclearNotification() {


        Intent simpleIntent = new Intent(this, AlertTranspatrentActivity.class);
        PendingIntent simplePendingIntent = PendingIntent.getActivity(
                this,
                0,
                simpleIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);



        Intent clipboardIntent = new Intent(this, AlertTranspatrentActivity.class);
        clipboardIntent.putExtra("request", getFromClipboard());
        PendingIntent clipboardPendingIntent = PendingIntent.getActivity(
                this,
                0,
                clipboardIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        PendingIntent settingsPendingIntent = PendingIntent.getActivity(
                this,
                0,
                settingsIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );


        Notification.Builder builder =
                new Notification.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(getString(R.string.search))
                        .setContentIntent(simplePendingIntent)
                        .setAutoCancel(false)
                        .addAction(android.R.drawable.ic_menu_preferences, "settings", settingsPendingIntent)
                        .addAction(android.R.drawable.ic_menu_set_as, "paste", clipboardPendingIntent);


        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1, builder.build());
    }

    public  String getFromClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String pasteData = "";
        ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);

        pasteData = item.getText().toString();
        return pasteData;
    }

}
