package com.upai.smartneib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.upai.smartneib.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.common_toolbar.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // 初始化
        init()
        // 按钮点击
        respondToClick()
    }

    private fun init() {
        // ActionBar, Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("登录")
    }

    private fun respondToClick() {
        btn_register.setOnClickListener { toRegisterActivity() }
    }

    private fun toRegisterActivity() {
        startActivity(RegisterActivity.getIntent(this))
    }

}
