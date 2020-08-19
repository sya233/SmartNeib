package com.upai.smartneib.notification_detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.widget.NestedScrollView
import com.upai.smartneib.R
import kotlinx.android.synthetic.main.activity_notification_detail.*
import kotlinx.android.synthetic.main.common_toolbar.*

class NotificationDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_detail)
        // 初始化
        init()
        // 点击事件
        respondToClick()
    }

    private fun init() {
        // 获取Intent传递的数据
        val id = intent.getStringExtra("id")
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        tv_title.text = title
        tv_content.text = Html.fromHtml(content, HtmlCompat.FROM_HTML_MODE_LEGACY)
        // ActionBar
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.nothing)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun respondToClick() {
        val title = intent.getStringExtra("title")
        nsv_notification_detail.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY > oldScrollY) {
                supportActionBar?.title = title
            } else if (scrollY < oldScrollY && scrollY == 0) {
                supportActionBar?.title = resources.getString(R.string.nothing)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun getIntent(context: Context, id: String, title: String, content: String): Intent {
            val intent = Intent(context, NotificationDetailActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("title", title)
            intent.putExtra("content", content)
            return intent
        }
    }

}