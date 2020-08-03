package com.upai.smartneib.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.upai.smartneib.R
import kotlinx.android.synthetic.main.common_toolbar.*

class MainActivity : AppCompatActivity(), MainView {

    // Presenter
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 初始化
        init()
    }

    private fun init() {
        // ActionBar, Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "登录成功"
        // Presenter
        mainPresenter = MainPresenter(this, MainModel())
        // 检查是否有版本更新
        mainPresenter.haveNewVersion(this)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun showToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

}