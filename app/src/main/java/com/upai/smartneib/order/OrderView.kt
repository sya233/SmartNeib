package com.upai.smartneib.order

interface OrderView {

    fun showToast(msg: String)

    fun updateRecyclerView(list: List<OrderPresenter.Order>)

    fun startAliPay(orderInfo: String)

}