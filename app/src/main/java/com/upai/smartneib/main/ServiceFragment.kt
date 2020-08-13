package com.upai.smartneib.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.upai.smartneib.R
import com.upai.smartneib.repair.RepairActivity
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
    }

    private fun toRepairActivity() {
        startActivity(RepairActivity.getIntent(serviceView.context))
    }

}