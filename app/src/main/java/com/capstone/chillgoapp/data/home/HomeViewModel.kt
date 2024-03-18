package com.capstone.chillgoapp.data.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.chillgoapp.data.TicketRepository
import com.capstone.chillgoapp.model.OrderTicket
import com.capstone.chillgoapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TicketRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderTicket>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderTicket>>>
        get() = _uiState

    fun getAllTickets() {
        viewModelScope.launch {
            repository.getAllTickets()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderTickets ->
                    _uiState.value = UiState.Success(orderTickets)
                }
        }
    }
}