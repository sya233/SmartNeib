package com.upai.smartneib.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upai.smartneib.R
import com.upai.smartneib.notification_detail.NotificationDetailActivity
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.fragment_notification.view.*
import kotlinx.android.synthetic.main.rv_item_notification.view.*

class NotificationFragment : Fragment(), NotificationView {

    // Presenter
    private lateinit var notificationPresenter: NotificationPresenter

    // Adapter
    private lateinit var adapter: NotificationAdapter

    // View
    private lateinit var notificationView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationView = inflater.inflate(R.layout.fragment_notification, container, false)
        init()
        // 刷新
        respondToRefresh()
        return notificationView
    }

    private fun init() {
        // Presenter
        notificationPresenter = NotificationPresenter(this, NotificationModel())
        // RecyclerView
        adapter = NotificationAdapter()
        notificationView.rv_notification.setHasFixedSize(true)
        notificationView.rv_notification.layoutManager =
            LinearLayoutManager(notificationView.rv_notification.context)
        notificationView.rv_notification.adapter = adapter
        // 获取通知列表
        notificationPresenter.getNotificationList()
    }

    private fun respondToRefresh() {
        notificationView.srl_notification.setOnRefreshListener {
            notificationPresenter.refreshRecyclerView()
        }
    }

    override fun showToast(msg: String) {
        (activity as MainActivity).runOnUiThread {
            Toast.makeText(notificationView.context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun updateRecyclerView(list: List<NotificationPresenter.Notification>) {
        activity?.runOnUiThread {
            adapter.updateList(list)
            adapter.notifyDataSetChanged()
        }
    }

    override fun finishRefresh() {
        activity?.runOnUiThread {
            notificationView.srl_notification.finishRefresh()
        }
    }

    // RecyclerView Adapter
    class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.Holder>() {
        private var notificationList: List<NotificationPresenter.Notification> =
            arrayListOf()

        fun updateList(list: List<NotificationPresenter.Notification>) {
            notificationList = list
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_notification, parent, false)
            return Holder(view)
        }

        override fun getItemCount(): Int {
            return notificationList.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.itemView.tv_title.text = notificationList[position].title
            holder.itemView.tv_des.text = notificationList[position].des
            // RecyclerView item click
            holder.itemView.setOnClickListener {
                holder.itemView.context.startActivity(
                    NotificationDetailActivity.getIntent(
                        holder.itemView.context,
                        notificationList[position].id,
                        notificationList[position].title,
                        notificationList[position].content
                    )
                )
            }
        }

        class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
    }

}