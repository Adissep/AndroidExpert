package com.capstone.chillgoapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.chillgoapp.R
import com.capstone.chillgoapp.ui.theme.ChillGoAppTheme
import com.capstone.chillgoapp.ui.theme.PrimaryMain

@Composable
fun CartItem(
    ticketId: Long,
    image: Int,
    title: String,
    totalPoint: Int,
    count: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier.padding(7.dp)) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .shadow(4.dp)
                .clip(RoundedCornerShape(16.dp)),
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(168.dp)
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(88.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                        .weight(1.0f)
                ) {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp,
                        color = PrimaryMain
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(
                                R.string.required_point,
                                totalPoint
                            ),
                            fontSize = 13.sp,
                            color = PrimaryMain
                        )
                        Icon(
                            modifier = Modifier.height(22.dp),
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "",
                            tint = PrimaryMain
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    ChillGoAppTheme {
        CartItem(
            4, R.drawable.thegreat_asiaafrika, "The Great Asia Afrika", 4000, 0,
        )
    }
}