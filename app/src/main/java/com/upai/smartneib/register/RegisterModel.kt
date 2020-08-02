package com.upai.smartneib.register

import com.upai.smartneib.util.baseUrl
import com.upai.smartneib.util.testUrl
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class RegisterModel {

    fun registerUser(user: String, pass: String, email: String, callback: okhttp3.Callback) {
        val client = OkHttpClient()

        val requestBody =
            FormBody.Builder().add("username", user).add("password", pass).add("email", email)
                .build()

        val request = Request.Builder().url(baseUrl + "register").post(requestBody).build()
        client.newCall(request).enqueue(callback)
    }

}