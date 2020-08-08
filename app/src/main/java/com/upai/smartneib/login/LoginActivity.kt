package com.upai.smartneib.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.upai.smartneib.R
import com.upai.smartneib.main.MainActivity
import com.upai.smartneib.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.common_toolbar.*

class LoginActivity : AppCompatActivity(), LoginView {

    // Presenter
    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // 初始化
        init()
        // 按钮点击
        respondToClick()
    }

    private fun init() {
        // Presenter
        loginPresenter = LoginPresenter(this, LoginModel())
        // ActionBar, Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "登录"
    }

    private fun respondToClick() {
        btn_login.setOnClickListener {
            loginPresenter.login(
                et_username.text.toString(),
                et_password.text.toString()
            )
        }
        btn_register.setOnClickListener { toRegisterActivity() }
    }

    override fun toRegisterActivity() {
        runOnUiThread {
            startActivity(RegisterActivity.getIntent(this))
        }
    }

    override fun toMainActivity() {
        runOnUiThread {
            startActivity(MainActivity.getIntent(this))
        }
    }

    override fun showToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

}
