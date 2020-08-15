package com.upai.smartneib.repair

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.upai.smartneib.R
import com.upai.smartneib.util.SharedPreferenceUtil
import kotlinx.android.synthetic.main.activity_repair.*
import kotlinx.android.synthetic.main.common_toolbar.*
import java.util.*

class RepairActivity : AppCompatActivity(), RepairView {

    // Presenter
    private lateinit var repairPresenter: RepairPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repair)
        // 初始化
        init()
        // 点击事件
        respondToClick()
    }

    private fun init() {
        // Toolbar, ActionBar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "报修"
        // Presenter
        repairPresenter = RepairPresenter(this, RepairModel())
    }

    private fun respondToClick() {
        btn_repair.setOnClickListener {
            val id = UUID.randomUUID().toString()
            val user = SharedPreferenceUtil.getUser(this)
            if (user != null) {
                repairPresenter.repair(
                    id,
                    user,
                    et_name.text.toString(),
                    et_phone.text.toString(),
                    et_address.text.toString(),
                    et_content.text.toString()
                )
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
        fun getIntent(context: Context): Intent {
            return Intent(context, RepairActivity::class.java)
        }
    }

    override fun showToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

}