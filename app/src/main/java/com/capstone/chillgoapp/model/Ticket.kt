package com.capstone.chillgoapp.model

data class Ticket(
    val id: Long,
    val image: Int,
    val title: String,
    val description: String,
    val requiredPoint: Int,
)