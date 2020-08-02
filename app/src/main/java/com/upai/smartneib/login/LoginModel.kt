package com.upai.smartneib.login

import com.upai.smartneib.util.baseUrl
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class LoginModel {

    fun login(user: String, pass: String, callback: okhttp3.Callback) {
        val client = OkHttpClient()

        val requestBody =
            FormBody.Builder().add("username", user).add("password", pass)
                .build()

        val request = Request.Builder().url(baseUrl + "login").post(requestBody).build()
        client.newCall(request).enqueue(callback)
    }

}