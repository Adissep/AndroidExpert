package com.capstone.chillgoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.chillgoapp.data.TicketRepository
import com.capstone.chillgoapp.data.cart.CartViewModel
import com.capstone.chillgoapp.data.detail.DetailTicketViewModel
import com.capstone.chillgoapp.data.home.HomeViewModel
import com.capstone.chillgoapp.data.more.MoreViewModel

class ViewModelFactory :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = TicketRepository()
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailTicketViewModel::class.java)) {
            return DetailTicketViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(MoreViewModel::class.java)) {
            return MoreViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(MoreViewModel::class.java)) {
            return MoreViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}