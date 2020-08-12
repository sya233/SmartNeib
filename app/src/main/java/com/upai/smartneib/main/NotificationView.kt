package com.upai.smartneib.main

interface NotificationView {

    fun showToast(msg: String)

    fun updateRecyclerView(list: List<NotificationPresenter.Notification>)

    fun finishRefresh()

}