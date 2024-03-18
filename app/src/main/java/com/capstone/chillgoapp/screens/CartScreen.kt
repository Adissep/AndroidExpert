package com.capstone.chillgoapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.chillgoapp.ViewModelFactory
import com.capstone.chillgoapp.components.CartItem
import com.capstone.chillgoapp.data.cart.CartState
import com.capstone.chillgoapp.data.cart.CartViewModel
import com.capstone.chillgoapp.ui.common.UiState

@Preview
@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory()
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedOrderTickets()
            }

            is UiState.Success -> {
                CartContent(uiState.data)
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun CartContent(
    state: CartState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            contentPadding = PaddingValues(7.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            columns = GridCells.Adaptive(160.dp)
        ) {
            items(state.orderTicket.size) { index ->
                val item = state.orderTicket[index]

                CartItem(
                    ticketId = item.ticket.id,
                    image = item.ticket.image,
                    title = item.ticket.title,
                    totalPoint = item.ticket.requiredPoint * item.count,
                    count = item.count,
                )
                Divider()
            }
        }
    }
}