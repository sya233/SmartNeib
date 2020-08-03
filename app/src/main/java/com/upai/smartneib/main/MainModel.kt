package com.upai.smartneib.main

import android.content.Context
import com.upai.smartneib.util.baseUrl
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class MainModel {

    // 检查是否有新版本
    fun haveNewVersion(context: Context, callback: Callback) {
        val currentVersion: String =
            context.packageManager.getPackageInfo(context.packageName, 0).versionName.toString()
        val client = OkHttpClient()
        val requestBody = FormBody.Builder().add("currentVersion", currentVersion).build()
        val request = Request.Builder().url(baseUrl + "update").post(requestBody).build()
        client.newCall(request).enqueue(callback)
    }

}