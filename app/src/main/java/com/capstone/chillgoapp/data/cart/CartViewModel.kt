package com.capstone.chillgoapp.data.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.chillgoapp.data.TicketRepository
import com.capstone.chillgoapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: TicketRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderTickets() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAllTickets()
                .collect { orderTicket ->
                    val totalRequiredPoint =
                        orderTicket.sumOf { it.ticket.requiredPoint * it.count }
                    _uiState.value = UiState.Success(CartState(orderTicket, totalRequiredPoint))
                }
        }
    }

    fun updateOrderTicket(ticketId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderTicket(ticketId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderTickets()
                    }
                }
        }
    }
}