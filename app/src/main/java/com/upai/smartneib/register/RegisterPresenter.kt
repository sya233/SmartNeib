package com.upai.smartneib.register

import android.util.Log
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class RegisterPresenter(
    private val registerView: RegisterView,
    private val registerModel: RegisterModel
) {

    fun registerUser(user: String, pass: String, email: String) {
        registerModel.registerUser(user, pass, email, object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                registerView.showToast("未知错误$e")
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response != null) {
                    val json: String? = response.body()?.string()
                    val register: Register = Gson().fromJson(json, Register::class.java)
                    registerView.showToast(register.result)
                }
            }

        })
    }

    data class Register(val username: String, val result: String)

}