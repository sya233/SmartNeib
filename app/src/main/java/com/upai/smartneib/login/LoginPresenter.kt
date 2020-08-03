package com.upai.smartneib.login

import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Response
import java.io.IOException

class LoginPresenter(private val loginView: LoginView, private val loginModel: LoginModel) {

    fun login(user: String, pass: String) {
        loginModel.login(user, pass, object : okhttp3.Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                loginView.showToast("未知错误$e")
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response != null) {
                    val json: String? = response.body()?.string()
                    val loginResult: LoginResult = Gson().fromJson(json, LoginResult::class.java)
                    when (loginResult.result) {
                        "登录成功!" -> {
                            loginView.showToast(loginResult.result)
                            loginView.toMainActivity()
                        }
                        else -> loginView.showToast(loginResult.result)
                    }
                }
            }

        })
    }

    data class LoginResult(val username: String, val result: String)

}