package com.example.accessibilitydemo

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationCaptureService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val packageName = sbn.packageName
        val notification = sbn.notification

        val extras = notification.extras

        val title = extras.getString("android.title")
        val text = extras.getCharSequence("android.text")

        Log.d(
            "NotificationCapture",
            "App: $packageName\nTitle: $title\nText: $text"
        )
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        Log.d(
            "NotificationCapture",
            "Notification removed from: ${sbn.packageName}"
        )
    }
}