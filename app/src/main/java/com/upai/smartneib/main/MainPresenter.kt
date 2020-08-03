package com.upai.smartneib.main

import android.content.Context
import android.text.TextUtils
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class MainPresenter(private val mainView: MainView, private val mainModel: MainModel) {

    // 检查是否有新版本
    fun haveNewVersion(context: Context) {
        mainModel.haveNewVersion(context, object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                mainView.showToast("发生错误: $e")
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response != null) {
                    val json = response.body()?.string()
                    val result: UpdateResult = Gson().fromJson(json, UpdateResult::class.java)
                    if (TextUtils.isEmpty(result.url)) {
                        mainView.showToast(result.result)
                    } else {
                        mainView.showToast(result.result + "下载地址: ${result.url}")
                    }
                }
            }

        })
    }

    data class UpdateResult(val version: String, val url: String, val result: String)

}