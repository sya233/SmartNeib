package com.upai.smartneib.repair

import com.upai.smartneib.util.baseUrl
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class RepairModel {

    fun repair(
        id: String,
        user: String,
        name: String,
        phone: String,
        address: String,
        content: String,
        callback: okhttp3.Callback
    ) {
        val client = OkHttpClient()
        val requestBody =
            FormBody.Builder().add("id", id).add("user", user).add("name", name).add("phone", phone)
                .add("address", address).add("content", content).build()
        val request = Request.Builder().url(baseUrl + "repair").post(requestBody).build()
        client.newCall(request).enqueue(callback)
    }

}