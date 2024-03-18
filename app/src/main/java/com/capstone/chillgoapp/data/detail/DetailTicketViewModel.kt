package com.capstone.chillgoapp.data.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.chillgoapp.data.TicketRepository
import com.capstone.chillgoapp.model.OrderTicket
import com.capstone.chillgoapp.model.Ticket
import com.capstone.chillgoapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailTicketViewModel(
    private val repository: TicketRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderTicket>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderTicket>>
        get() = _uiState

    fun getTicketById(ticketId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderTicketById(ticketId))
        }
    }

    fun addToCart(ticket: Ticket, count: Int) {
        viewModelScope.launch {
            repository.updateOrderTicket(ticket.id, count)
        }
    }
}