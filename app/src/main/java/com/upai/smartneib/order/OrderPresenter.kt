package com.upai.smartneib.order

import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class OrderPresenter(private val orderView: OrderView, private val orderModel: OrderModel) {

    fun getOrderList(user: String) {
        orderModel.getOrderList(user, object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                orderView.showToast("未知错误$e")
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response != null) {
                    val json: String? = response.body()?.string()
                    val result = Gson().fromJson(json, OrderResult::class.java)
                    orderView.updateRecyclerView(result.aliorderList)
                }
            }

        })
    }

    fun getOrderInfo(des: String, id: String, amount: String) {
        orderModel.getOrderInfo(des, id, amount, object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                orderView.showToast("未知错误$e")
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response != null) {
                    val json = response.body().string()
                    orderView.startAliPay(json)
                }
            }

        })
    }

    data class OrderResult(val aliorderList: List<Order>, val result: String)

    data class Order(
        val id: String,
        val user: String,
        val amount: String,
        val des: String,
        val status: String
    )

}