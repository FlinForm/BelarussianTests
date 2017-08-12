package by.test.belarussian.belarussiantests.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import by.test.belarussian.belarussiantests.R;
import by.test.belarussian.belarussiantests.activities.MainActivity;

public class NotificationService extends IntentService {
    public NotificationService() {
        super("Notification Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        while (true) {
            try {
                Thread.sleep(1000 * 60 * 60 * 6);
                showNotification();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.notification_text));

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        int notificationId = 13;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(notificationId, builder.build());
    }
}
