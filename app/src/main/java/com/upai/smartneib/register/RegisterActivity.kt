package com.upai.smartneib.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.upai.smartneib.R
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.common_toolbar.*

class RegisterActivity : AppCompatActivity(), RegisterView {

    private lateinit var registerPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        // 初始化
        init()
        // 按钮点击
        respondToClick()
    }

    private fun init() {
        // ActionBar, Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "注册"
        // Presenter
        registerPresenter = RegisterPresenter(this, RegisterModel())
    }

    private fun respondToClick() {
        btn_register.setOnClickListener {
            registerPresenter.registerUser(
                et_username.text.toString(),
                et_password.text.toString(),
                et_email.text.toString()
            )
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun showToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

}