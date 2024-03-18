package com.capstone.chillgoapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.chillgoapp.screens.BottomSection
import com.capstone.chillgoapp.ui.theme.PrimaryMain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)

@Composable
fun FirstPage(
    scope: CoroutineScope,
    pageState: PagerState
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Where do you prefer to vacation?",
            fontSize = 16.sp,
            color = PrimaryMain,
            fontWeight = FontWeight.W700
        )
        Spacer(modifier = Modifier.height(67.dp))
        Row {
            OnBoardingChip(label = "Beach")
            Spacer(modifier = Modifier.width(12.dp))
            OnBoardingChip(label = "Waterfall")
            Spacer(modifier = Modifier.width(12.dp))
            OnBoardingChip(label = "Snorkeling")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row {
            OnBoardingChip(label = "Driving")
            Spacer(modifier = Modifier.width(12.dp))
            OnBoardingChip(label = "Nature")
            Spacer(modifier = Modifier.width(12.dp))
            OnBoardingChip(label = "Garden")
        }
        Spacer(modifier = Modifier.height(142.dp))
        BottomSection(index = pageState.currentPage) {
            if (pageState.currentPage + 1 < 2) scope.launch {
                pageState.scrollToPage(pageState.currentPage + 1)
            }
        }
        Spacer(modifier = Modifier.height(47.dp))
        OnBoardingButton(label = "Next") {
            if (pageState.currentPage + 1 < 2) scope.launch {
                pageState.scrollToPage(pageState.currentPage + 1)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        OnBoardingButton(label = "Skip", outlined = true) {
            if (pageState.currentPage + 1 < 2) scope.launch {
                pageState.scrollToPage(pageState.currentPage + 1)
            }
        }
    }
}


@Composable
fun OnBoardingChip(
    label: String
) {
    Surface(
        modifier = Modifier
            .clickable { },
        shape = RoundedCornerShape(100.dp),
        border = BorderStroke(width = 1.dp, color = PrimaryMain)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = label,
            color = PrimaryMain,
            fontSize = 18.sp
        )
    }
}

@Composable
fun OnBoardingButton(
    label: String,
    outlined: Boolean = false,
    onButtonClick: () -> Unit
) {
    if (outlined) OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        border = BorderStroke(width = 1.dp, color = PrimaryMain),
        onClick = { onButtonClick() }) {
        Text(
            text = label,
            fontWeight = FontWeight.W600,
            color = PrimaryMain
        )
    } else Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryMain,
        ),
        onClick = { onButtonClick() }) {
        Text(
            text = label,
            fontWeight = FontWeight.W600
        )
    }
}