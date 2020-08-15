package com.upai.smartneib.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alipay.sdk.app.EnvUtils
import com.alipay.sdk.app.PayTask
import com.upai.smartneib.R
import com.upai.smartneib.order.OrderActivity
import com.upai.smartneib.repair.RepairActivity
import com.upai.smartneib.util.PayResult
import kotlinx.android.synthetic.main.fragment_service.view.*

class ServiceFragment : Fragment() {

    private lateinit var serviceView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        serviceView = inflater.inflate(R.layout.fragment_service, container, false)
        // 初始化
        init()
        // 点击事件
        respondToClick()
        return serviceView
    }

    private fun init() {

    }

    private fun respondToClick() {
        serviceView.cv_repair.setOnClickListener { toRepairActivity() }
        serviceView.cv_pay.setOnClickListener { toOrderActivity() }
    }

    private fun toRepairActivity() {
        startActivity(RepairActivity.getIntent(serviceView.context))
    }

    private fun toOrderActivity() {
        startActivity(OrderActivity.getIntent(serviceView.context))
    }

}