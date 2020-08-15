package com.upai.smartneib.repair

import android.util.Log
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Response
import java.io.IOException

class RepairPresenter(private val repairView: RepairView, private val repairModel: RepairModel) {

    fun repair(
        id: String,
        user: String,
        name: String,
        phone: String,
        address: String,
        content: String
    ) {
        repairModel.repair(id, user, name, phone, address, content, object : okhttp3.Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                repairView.showToast("未知错误$e")
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response != null) {
                    val json: String? = response.body()?.string()
                    Log.d("RepairP", "json是: $json")
                    val repair = Gson().fromJson(json, Repair::class.java)
                    Log.d("RepairP", "repair是: $repair")
                    repairView.showToast(repair.result)
                }
            }

        })
    }

    data class Repair(val user: String, val name: String, val result: String)

}