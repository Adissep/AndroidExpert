package com.capstone.chillgoapp.screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.capstone.chillgoapp.components.FirstPage
import com.capstone.chillgoapp.components.SecondPage
import com.capstone.chillgoapp.ui.theme.PrimaryBorder
import com.capstone.chillgoapp.ui.theme.PrimaryMain

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState { 2 }

    HorizontalPager(
        state = pageState,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) { _ ->
        if (pageState.currentPage == 0) FirstPage(scope = scope, pageState = pageState)
        else SecondPage(
            scope = scope,
            pageState = pageState,
            onNavigateToLogin = onNavigateToLogin,
            onNavigateToSignup = onNavigateToSignUp
        )
    }
}

@Composable
fun BottomSection(index: Int, onButtonClick: () -> Unit = {}) {
    Box(
        modifier = Modifier.padding(12.dp)
    ) {
        Indicators(index)
    }
}

@Composable
fun BoxScope.Indicators(index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(2) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy), label = ""
    )

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) PrimaryMain else PrimaryBorder
            )
    ) {}
}