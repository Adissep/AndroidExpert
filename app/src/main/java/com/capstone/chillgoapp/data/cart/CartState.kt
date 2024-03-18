package com.capstone.chillgoapp.data.cart

import com.capstone.chillgoapp.model.OrderTicket

data class CartState(
    val orderTicket: List<OrderTicket>,
    val totalRequiredPoint: Int
)