package com.capstone.chillgoapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.chillgoapp.ViewModelFactory
import com.capstone.chillgoapp.components.MoreItem
import com.capstone.chillgoapp.components.MoreSearch
import com.capstone.chillgoapp.components.NormalTextComponent
import com.capstone.chillgoapp.data.more.MoreViewModel
import com.capstone.chillgoapp.model.OrderTicket
import com.capstone.chillgoapp.ui.common.UiState
import com.capstone.chillgoapp.ui.theme.PrimaryMain

@Composable
fun MoreScreen(
    modifier: Modifier = Modifier,
    moreViewModel: MoreViewModel = viewModel(
        factory = ViewModelFactory()
    ),
    navigateToDetail: (Long) -> Unit = {},
) {
    moreViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                moreViewModel.getAllTickets()
            }

            is UiState.Success -> {
                Column(
                    modifier = Modifier.background(Color.White)
                ) {
                    MoreSearch()
                    MoreChips()
                    Spacer(modifier = Modifier.height(15.dp))
                    MoreContent(orderTicket = uiState.data, navigateToDetail = navigateToDetail)
                }
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun MoreChips() {
    Row {
        MoreChip(
            modifier = Modifier.padding(start = 16.dp),
            label = "Sukabumi",
            leadingIcon = {
                Icon(
                    modifier = Modifier.height(18.dp),
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = PrimaryMain
                )
            }
        )
        MoreChip(
            modifier = Modifier.padding(start = 16.dp),
            label = "Yang sering dikunjungi",
        )
    }
}

@Composable
fun MoreChip(
    modifier: Modifier = Modifier,
    label: String = "Label",
    leadingIcon: @Composable (() -> Unit)? = {}
) {
    Surface(
        modifier = modifier,
        color = Color(0X80C7CEBE),
        shape = RoundedCornerShape(15.dp)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 14.dp)
        ) {
            if (leadingIcon != null) leadingIcon()
            if (leadingIcon != null) Spacer(modifier = Modifier.width(5.dp))
            NormalTextComponent(
                value = label,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun MoreContent(
    orderTicket: List<OrderTicket>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier.testTag("TicketList")
    ) {
        items(orderTicket.size) { index ->
            val data = orderTicket[index]

            MoreItem(
                image = data.ticket.image,
                title = data.ticket.title,
                description = data.ticket.description,
                requiredPoint = data.ticket.requiredPoint,
                modifier = Modifier.clickable {
                    navigateToDetail(data.ticket.id)
                }
            )
        }
    }
}

@Preview
@Composable
fun MoreScreenPreview() {
    MoreScreen()
}