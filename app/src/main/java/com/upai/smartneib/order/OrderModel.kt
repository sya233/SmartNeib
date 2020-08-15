package com.upai.smartneib.order

import com.upai.smartneib.util.baseUrl
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class OrderModel {

    fun getOrderList(user: String, callback: Callback) {
        val client = OkHttpClient()
        val requestBody = FormBody.Builder().add("user", user).build()
        val request = Request.Builder().url(baseUrl + "order").post(requestBody).build()
        client.newCall(request).enqueue(callback)
    }

    fun getOrderInfo(des: String, id: String, amount: String, callback: Callback) {
        val client = OkHttpClient()
        val requestBody =
            FormBody.Builder().add("des", des).add("id", id).add("amount", amount).build()
        val request = Request.Builder().url(baseUrl + "pay").post(requestBody).build()
        client.newCall(request).enqueue(callback)
    }

}