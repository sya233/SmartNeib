package com.upai.smartneib.util

import android.content.Context
import okhttp3.*
import okio.BufferedSink
import okio.BufferedSource
import okio.Okio
import java.io.File
import java.io.IOException

val baseUrl = "http://39.108.96.235:8080/smart-neib/"

// 升级APK
fun updateApk(url: String, path: String) {
    val client: OkHttpClient = OkHttpClient()

    val request: Request = Request.Builder()
        .url(url)
        .build();

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call?, e: IOException?) {
            TODO("Not yet implemented")
        }

        override fun onResponse(call: Call?, response: Response?) {
            if (response != null) {
                val source: BufferedSource = response.body().source()
                val file: File = File(path, "app.apk")
                val sink: BufferedSink = Okio.buffer(Okio.sink(file))
                sink.writeAll(source)
                sink.flush()
                sink.close()
            }
        }

    })
}