package com.capstone.chillgoapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.chillgoapp.R
import com.capstone.chillgoapp.screens.BottomSection
import com.capstone.chillgoapp.ui.theme.PrimaryBorder
import com.capstone.chillgoapp.ui.theme.PrimaryMain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SecondPage(
    scope: CoroutineScope = rememberCoroutineScope(),
    pageState: PagerState,
    onNavigateToLogin: () -> Unit = {},
    onNavigateToSignup: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .width(157.dp)
                .height(125.dp)
                .padding(top = 16.dp),
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(17.dp))
        Text(
            text = "Please do Sign In or Sign Up!",
            fontSize = 16.sp,
            color = PrimaryMain,
            fontWeight = FontWeight.W700
        )
        Spacer(modifier = Modifier.height(146.dp))
        Image(
            modifier = Modifier.width(105.dp),
            painter = painterResource(id = R.drawable.image),
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(108.dp))
        BottomSection(index = pageState.currentPage) {
            if (pageState.currentPage + 1 < 2) scope.launch {
                pageState.scrollToPage(pageState.currentPage + 1)
            }
        }
        Spacer(modifier = Modifier.height(47.dp))
        Row(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                border = BorderStroke(width = 1.dp, color = PrimaryMain),
                onClick = { onNavigateToLogin() }) {
                Text(
                    text = "Sign In",
                    fontWeight = FontWeight.W600,
                    color = PrimaryMain
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Button(
                modifier = Modifier.weight(1f),
                border = BorderStroke(width = 1.dp, color = PrimaryMain),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryMain
                ),
                onClick = { onNavigateToSignup() }) {
                Text(
                    text = "Sign Up",
                    fontWeight = FontWeight.W600
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        DividerTextComponent()
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Surface(
                shape = CircleShape,
                border = BorderStroke(width = 1.dp, color = PrimaryBorder)
            ) {
                Image(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp),
                    painter = painterResource(id = R.drawable.fb_logo),
                    contentDescription = ""
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Surface(
                shape = CircleShape,
                border = BorderStroke(width = 1.dp, color = PrimaryBorder)
            ) {
                Image(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(30.dp),
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.height(33.dp))
    }
}