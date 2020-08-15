package com.upai.smartneib.main

import android.util.Log
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class NotificationPresenter(
    private val notificationView: NotificationView,
    private val notificationModel: NotificationModel
) {

    fun getNotificationList() {
        notificationModel.getNotificationList(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                notificationView.showToast("未知错误$e")
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response != null) {
                    val json: String? = response.body()?.string()
                    val result: NotificationResult =
                        Gson().fromJson(json, NotificationResult::class.java)
                    notificationView.updateRecyclerView(result.notification)
                }
            }

        })
    }

    // 刷新
    fun refreshRecyclerView() {
        notificationModel.getNotificationList(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                notificationView.showToast("未知错误$e")
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response != null) {
                    val json: String? = response.body()?.string()
                    val result: NotificationResult =
                        Gson().fromJson(json, NotificationResult::class.java)
                    notificationView.updateRecyclerView(result.notification)
                    notificationView.finishRefresh()
                }
            }

        })
    }

    // bean
    data class NotificationResult(val result: String, val notification: MutableList<Notification>)
    data class Notification(val id: String, val title: String, val des: String, val content: String)
}