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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.chillgoapp.R
import com.capstone.chillgoapp.ui.theme.ChillGoAppTheme
import com.capstone.chillgoapp.ui.theme.PrimaryMain
import com.gowtham.ratingbar.RatingBar

@Composable
fun MoreItem(
    image: Int,
    title: String,
    description: String,
    requiredPoint: Int,
    modifier: Modifier = Modifier,
) {
    Box(modifier = Modifier.padding(bottom = 28.dp)) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .shadow(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(113.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )
                Spacer(modifier = Modifier.height(14.dp))
                Column(
                    modifier = Modifier.padding(start = 8.dp, end = 18.dp, bottom = 11.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W500,
                            color = PrimaryMain
                        )
                        Text(
                            text = stringResource(R.string.required_point, requiredPoint),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W500,
                            color = PrimaryMain
                        )
                    }
                    Spacer(modifier = Modifier.height(27.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        RatingBar(
                            value = 4f,
                            painterEmpty = painterResource(id = R.drawable.baseline_star_border_24),
                            painterFilled = painterResource(id = R.drawable.baseline_star_24),
                            spaceBetween = 2.dp,
                            onValueChange = {},
                            onRatingChanged = {})
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "",
                            tint = PrimaryMain
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text = "7",
                            color = PrimaryMain,
                            fontWeight = FontWeight.W500
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Icon(
                            imageVector = Icons.Default.Chat,
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
fun MoreItemPreview() {
    ChillGoAppTheme {
        MoreItem(
            R.drawable.thegreat_asiaafrika, "The Great Asia Afrika", "", 40000,
            modifier = Modifier.padding(8.dp)
        )
    }
}