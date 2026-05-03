package com.arogyasahaya.app.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.arogyasahaya.app.R;
import com.arogyasahaya.app.ui.MainActivity;

public class MedicationAlarmReceiver extends BroadcastReceiver {

    public static final String CHANNEL_ID = "medication_reminders";
    public static final String EXTRA_MEDICINE_NAME = "medicine_name";
    public static final String EXTRA_MEDICINE_ID = "medicine_id";
    public static final String EXTRA_TIME_SLOT = "time_slot";
    public static final String ACTION_MEDICATION_ALARM = "com.arogyasahaya.MEDICATION_ALARM";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            // Re-schedule all alarms after device reboot
            AlarmScheduler.rescheduleAllAlarms(context);
            return;
        }

        if (ACTION_MEDICATION_ALARM.equals(action)) {
            String medicineName = intent.getStringExtra(EXTRA_MEDICINE_NAME);
            int medicineId = intent.getIntExtra(EXTRA_MEDICINE_ID, 0);
            String timeSlot = intent.getStringExtra(EXTRA_TIME_SLOT);

            showMedicationNotification(context, medicineName, medicineId, timeSlot);
        }
    }

    private void showMedicationNotification(Context context, String medicineName,
                                             int medicineId, String timeSlot) {
        createNotificationChannel(context);

        Intent mainIntent = new Intent(context, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, medicineId, mainIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        String timeLabel = timeSlot != null ? timeSlot : "";
        String body = timeLabel + " dose: " + (medicineName != null ? medicineName : "Your medicine");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_medication)
                .setContentTitle("💊 Medicine Time!")
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("It's time to take your " + body + ". Stay healthy! 🌿"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setAutoCancel(true)
                .setVibrate(new long[]{0, 500, 200, 500})
                .setContentIntent(pendingIntent)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(medicineId + 1000, builder.build());
        }
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Medication Reminders",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Reminds you to take your medicines on time");
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{0, 500, 200, 500});
            channel.setShowBadge(true);

            NotificationManager notificationManager =
                    context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
