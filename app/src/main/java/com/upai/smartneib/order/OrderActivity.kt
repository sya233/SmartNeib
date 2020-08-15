package com.upai.smartneib.order

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alipay.sdk.app.EnvUtils
import com.alipay.sdk.app.PayTask
import com.upai.smartneib.R
import com.upai.smartneib.util.PayResult
import com.upai.smartneib.util.SharedPreferenceUtil
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.common_toolbar.*
import kotlinx.android.synthetic.main.rv_item_order.view.*

class OrderActivity : AppCompatActivity(), OrderView {

    // Presenter
    private lateinit var orderPresenter: OrderPresenter

    // RecyclerView Adapter
    private lateinit var adapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        // 初始化
        init()
    }

    private fun init() {
        // Presenter
        orderPresenter = OrderPresenter(this, OrderModel())
        // ActionBar, Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "缴费列表"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // RecyclerView
        adapter = OrderAdapter()
        rv_order.layoutManager = LinearLayoutManager(rv_order.context)
        rv_order.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        rv_order.adapter = adapter
        // 获取缴费订单
        val user = SharedPreferenceUtil.getUser(this)
        if (user != null) {
            orderPresenter.getOrderList(user)
        }
        // 支付宝沙箱环境
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)
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

    // RecyclerView Adapter
    inner class OrderAdapter : RecyclerView.Adapter<OrderAdapter.Holder>() {

        private var orderList: List<OrderPresenter.Order> = arrayListOf()

        fun updateList(list: List<OrderPresenter.Order>) {
            orderList = list
        }

        inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.rv_item_order, parent, false)
            return Holder(view)
        }

        override fun getItemCount(): Int {
            return orderList.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            if (orderList[position].status == "已支付") {
                holder.itemView.tv_status.setTextColor(Color.GREEN)
            }
            holder.itemView.tv_des.text = orderList[position].des
            holder.itemView.tv_amount.text = orderList[position].amount
            holder.itemView.tv_status.text = orderList[position].status
            holder.itemView.setOnClickListener {
                orderPresenter.getOrderInfo(
                    orderList[position].des,
                    orderList[position].id,
                    orderList[position].amount
                )
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, OrderActivity::class.java)
        }
    }

    override fun showToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun updateRecyclerView(list: List<OrderPresenter.Order>) {
        runOnUiThread {
            adapter.updateList(list)
            adapter.notifyDataSetChanged()
        }
    }

    override fun startAliPay(orderInfo: String) {
        Thread(Runnable {
            val alipay = PayTask(this)
            val result = alipay.payV2(orderInfo, true)
            val msg = Message()
            msg.what = 1
            msg.obj = result
            mHandler.sendMessage(msg)
        }).start()
    }

    @Suppress("UNCHECKED_CAST")
    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {
                    val payResult = PayResult(msg.obj as Map<String, String>)
                    val resultStatus = payResult.resultStatus
                    if (TextUtils.equals(resultStatus, "9000")) {
                        showToast("支付成功")
                    } else {
                        showToast("支付失败")
                    }
                }
            }
        }
    }
}