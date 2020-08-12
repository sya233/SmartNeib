package com.upai.smartneib.main

import com.upai.smartneib.util.baseUrl
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class NotificationModel {

    fun getNotificationList(callback: okhttp3.Callback) {
        val client = OkHttpClient()
        val requestBody = FormBody.Builder().build()
        val request = Request.Builder().url(baseUrl + "notification").post(requestBody).build()
        client.newCall(request).enqueue(callback)
    }

}