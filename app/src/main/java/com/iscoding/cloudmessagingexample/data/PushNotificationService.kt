package com.iscoding.cloudmessagingexample.data


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.iscoding.cloudmessagingexample.R
import com.iscoding.cloudmessagingexample.presentation.MainActivity
import java.lang.Exception

class PushNotificationService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        // Update server
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        // Respond to received messages


        Log.d("ISLAM", "From: ${message.from}")

        // Check if message contains a data payload
        message.data.isNotEmpty().let {
            Log.d("ISLAM", "Message data payload: " + message.data)
            handleDataMessage(message.data)
        }

        // Check if message contains a notification payload
        message.notification?.let {
            Log.d("ISLAM", "Message Notification Body: ${it.body}")
            sendNotification(it.body)
        }

    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }

    override fun onMessageSent(msgId: String) {
        super.onMessageSent(msgId)
    }

    override fun onSendError(msgId: String, exception: Exception) {
        super.onSendError(msgId, exception)
    }

    private fun handleDataMessage(data: Map<String, String>) {
        // Handle the data message
        // Example:
        val customKey = data["customKey"]
        Log.d("ISLAM", "Custom key value: $customKey")
        // Process data payload
    }

    private fun sendNotification(messageBody: String?) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create the notification channel if necessary (for Android O and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "default_channel",
                "Default Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Default Channel for App Notifications"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, "default_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Make sure you have this drawable
            .setContentTitle("FCM Message")
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Ensure it gets attention
            .setContentIntent(PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE))

        notificationManager.notify(0, notificationBuilder.build())
    }



}