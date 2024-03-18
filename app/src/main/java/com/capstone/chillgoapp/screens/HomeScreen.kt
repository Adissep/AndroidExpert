package com.capstone.chillgoapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.chillgoapp.R
import com.capstone.chillgoapp.ViewModelFactory
import com.capstone.chillgoapp.components.CityItem
import com.capstone.chillgoapp.components.HomeSection
import com.capstone.chillgoapp.components.Search
import com.capstone.chillgoapp.components.TicketItem
import com.capstone.chillgoapp.data.home.HomeViewModel
import com.capstone.chillgoapp.model.OrderTicket
import com.capstone.chillgoapp.ui.common.UiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory()
    ),
    navigateToDetail: (Long) -> Unit = {},
    navigateToMore: () -> Unit = {},
) {
    homeViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                homeViewModel.getAllTickets()
            }

            is UiState.Success -> {
                Column {
                    Banner()
                    Spacer(modifier = Modifier.height(28.dp))
                    HomeSection(
                        title = stringResource(R.string.nearby_tours),
                        showLocation = true,
                        content = {
                            HomeContent(
                                orderTicket = uiState.data,
                                modifier = modifier,
                                navigateToDetail = navigateToDetail,
                            )
                        },
                        onTextSelected = {
                            navigateToMore()
                        }
                    )

                    HomeSection(
                        title = stringResource(R.string.berdasar_kota),
                        content = {
                            HomeCity(
                                modifier = modifier,
                                navigateToDetail = navigateToDetail,
                            )
                        },
                        onTextSelected = {
                            navigateToMore()
                        }
                    )

                    HomeSection(
                        title = stringResource(R.string.frequently_visited),
                        content = {
                            HomeContent(
                                orderTicket = uiState.data,
                                modifier = modifier,
                                navigateToDetail = navigateToDetail,
                            )
                        },
                        onTextSelected = {
                            navigateToMore()
                        }
                    )
                }
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeCity(
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    val cities = listOf(
        "Bandung", "Jakarta", "Yogyakarta", "Semarang", "Surabaya", "Malang"
    )

    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("TicketList")
    ) {
        items(cities.size) { index ->
            val data = cities[index]

            CityItem(
                image = R.drawable.bukit_bintang_bandung,
                title = data,
                modifier = Modifier.clickable {
                    navigateToDetail(1)
                }
            )
        }
    }
}

@Composable
fun HomeContent(
    orderTicket: List<OrderTicket>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.testTag("TicketList")
    ) {
        items(orderTicket.size) { index ->
            val data = orderTicket[index]

            TicketItem(
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

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp),
        )
        Search()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}