package com.capstone.chillgoapp.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capstone.chillgoapp.R
import com.capstone.chillgoapp.ViewModelFactory
import com.capstone.chillgoapp.data.detail.DetailTicketViewModel
import com.capstone.chillgoapp.ui.common.UiState
import com.capstone.chillgoapp.ui.theme.ChillGoAppTheme
import com.capstone.chillgoapp.ui.theme.PrimaryBorder
import com.capstone.chillgoapp.ui.theme.PrimaryMain
import com.gowtham.ratingbar.RatingBar

@Composable
fun DetailScreen(
    ticketId: Long,
    viewModel: DetailTicketViewModel = viewModel(
        factory = ViewModelFactory()
    ),
//    navigateBack: () -> Unit,
//    navigateToCart: () -> Unit,
    navigateToReviews: () -> Unit,
    navigateToUmkmDetail: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getTicketById(ticketId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.ticket.image,
                    data.ticket.title,
                    data.ticket.description,
                    data.ticket.requiredPoint,
                    data.count,
//                    onBackClick = navigateBack,
//                    onAddToCart = { count ->
//                        viewModel.addToCart(data.ticket, count)
//                        navigateToCart()
//                    }
                    navigateToReviews = navigateToReviews,
                    navigateToUmkmDetail = navigateToUmkmDetail
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    title: String,
    description: String,
    basePoint: Int,
    count: Int,
//    onBackClick: () -> Unit,
//    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
    navigateToReviews: () -> Unit = {},
    navigateToUmkmDetail: () -> Unit
) {

    Box {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .height(250.dp)
                .fillMaxWidth()
        )
        DetailBody(
            title = title,
            basePoint = basePoint,
            description = description,
            navigateToReviews = navigateToReviews,
            navigateToUmkmDetail = navigateToUmkmDetail
        )
    }
}

@Composable
fun DetailBody(
    title: String,
    basePoint: Int,
    description: String,
    navigateToReviews: () -> Unit = {},
    navigateToUmkmDetail: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(top = 229.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 28.dp, end = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            DetailBodyHeader(
                title = title,
                basePoint = basePoint,
                description = description
            )
            Divider(
                modifier = Modifier.padding(
                    top = 29.dp, bottom = 9.dp
                ),
                thickness = 1.dp,
                color = PrimaryBorder
            )
            DetailBodyContent(
                navigateToReviews = navigateToReviews,
                navigateToUmkmDetail = navigateToUmkmDetail
            )
        }
    }
}

@Composable
fun DetailBodyHeader(
    title: String,
    basePoint: Int,
    description: String
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                textAlign = TextAlign.Start,
                fontSize = 32.sp,
                fontWeight = FontWeight.W600,
                color = PrimaryMain,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.required_point, basePoint),
                fontSize = 20.sp,
                fontWeight = FontWeight.W700,
                color = PrimaryMain,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = "",
                tint = PrimaryMain
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Indonesia",
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                color = PrimaryMain,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "",
                tint = PrimaryMain
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "4.6",
                fontSize = 16.sp,
                fontWeight = FontWeight.W700,
                color = PrimaryMain,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "4 rb+ rating",
                fontSize = 16.sp,
                color = PrimaryMain,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "About",
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            color = PrimaryMain,
        )
        Text(
            text = description,
            fontSize = 16.sp,
            color = PrimaryMain,
        )
    }
}

@Composable
fun DetailBodyContent(
    navigateToReviews: () -> Unit,
    navigateToUmkmDetail: () -> Unit
) {
    var review by remember {
        mutableStateOf("")
    }
    var rating by remember {
        mutableStateOf(4f)
    }

    val days = listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")

    Column {
        Text(
            text = "Opening Hours",
            color = PrimaryMain,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700
        )

        days.forEach {
            Row(
                modifier = Modifier.padding(vertical = 9.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = "",
                    tint = PrimaryMain
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = it,
                    color = PrimaryMain,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "08.00 - 16.00 WIB",
                    color = PrimaryMain,
                    fontSize = 16.sp
                )
            }
        }

        Divider(
            modifier = Modifier.padding(
                top = 29.dp, bottom = 14.dp
            ),
            thickness = 1.dp,
            color = PrimaryBorder
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Storefront,
                contentDescription = "",
                tint = PrimaryMain
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "UMKM AROUND HERE!",
                color = PrimaryMain,
                fontWeight = FontWeight.W600
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(3) { _ ->
                Column(
                    modifier = Modifier.clickable { navigateToUmkmDetail() },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(86.dp),
                        painter = painterResource(id = R.drawable.umkm),
                        contentDescription = "",
                    )
                    Spacer(modifier = Modifier.height(13.dp))
                    Text(
                        text = "Judul",
                        color = PrimaryMain,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600
                    )
                }
            }
        }
        Divider(
            modifier = Modifier.padding(vertical = 9.dp),
            thickness = 1.dp,
            color = PrimaryBorder
        )
        TextField(
            value = review,
            placeholder = {
                Text(
                    text = "Write your review here..",
                    fontWeight = FontWeight.W600,
                    color = PrimaryMain
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            onValueChange = {
                review = it
            })
        Spacer(modifier = Modifier.height(24.dp))
        RatingBar(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            value = rating,
            painterEmpty = painterResource(id = R.drawable.baseline_star_border_24),
            painterFilled = painterResource(id = R.drawable.baseline_star_24),
            spaceBetween = 2.dp,
            size = 32.dp,
            onValueChange = { rating = it },
            onRatingChanged = {})
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            shape = RoundedCornerShape(15.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 9.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0X80C7CEBE)
            ),
            onClick = { }) {
            Icon(
                imageVector = Icons.Default.EditNote,
                contentDescription = "",
                tint = PrimaryMain
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Review",
                color = PrimaryMain,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(9.dp))
        Button(
            modifier = Modifier.align(Alignment.End),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryMain
            ),
            onClick = { }) {
            Text(text = "Posting")
        }
        Spacer(modifier = Modifier.height(4.dp))
        repeat(2) { ReviewItem() }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { navigateToReviews() },
            text = "Lihat semua ulasan",
            color = PrimaryMain
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun ReviewItem() {
    Column(
        modifier = Modifier.padding(bottom = 14.dp)
    ) {
        Row {
            Icon(
                modifier = Modifier.size(21.dp),
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = PrimaryMain
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Name",
                fontSize = 12.sp,
                color = PrimaryMain
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        RatingBar(
            value = 4f,
            painterEmpty = painterResource(id = R.drawable.baseline_star_border_24),
            painterFilled = painterResource(id = R.drawable.baseline_star_24),
            spaceBetween = 2.dp,
            size = 14.dp,
            onValueChange = { },
            onRatingChanged = {})
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Deskripsi Ulasan",
            fontSize = 12.sp,
            color = PrimaryMain
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    ChillGoAppTheme {
        DetailContent(
            R.drawable.thegreat_asiaafrika,
            "The Great Asia Afrika",
            "Lorem Ipsum Preview",
            50000,
            1,
//            onBackClick = {},
//            onAddToCart = {},
            navigateToUmkmDetail = {}
        )
    }
}