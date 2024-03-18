package com.capstone.chillgoapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capstone.chillgoapp.R
import com.capstone.chillgoapp.ui.theme.PrimaryBorder
import com.capstone.chillgoapp.ui.theme.PrimaryMain
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun UmkmMapScreen() {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val mumbai = LatLng(19.0760, 72.8777)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(mumbai, 11f)
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 24.dp,
            topEnd = 24.dp
        ),
        sheetDragHandle = {
            Image(
                modifier = Modifier
                    .padding(vertical = 18.dp)
                    .width(54.dp)
                    .height(24.dp),
                painter = painterResource(id = R.drawable.drag),
                contentDescription = ""
            )
        },
        sheetContent = {
            UmkmDetail()
        }) {
        Surface {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            )
        }
    }
}

@Composable
fun UmkmDetail() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .heightIn(max = (screenHeight * .8).dp)
            .verticalScroll(rememberScrollState())
    ) {
        UmkmHeader()
    }
}

@Composable
fun UmkmHeader() {
    Column {
        Surface(
            modifier = Modifier.size(114.dp),
            color = Color(0XCCC7CEBE),
            shape = CircleShape,
        ) {
            Image(
                painter = painterResource(id = R.drawable.account),
                contentDescription = "Profile Picture",
                modifier = Modifier.padding(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Name UMKM",
            textAlign = TextAlign.Start,
            fontSize = 32.sp,
            fontWeight = FontWeight.W600,
            color = PrimaryMain,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Jl. Terusan, Paster, Bandung",
            fontSize = 16.sp,
            fontWeight = FontWeight.W700,
            color = PrimaryMain,
        )
        Spacer(modifier = Modifier.height(24.dp))
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
        Row {
            Icon(
                imageVector = Icons.Default.VerifiedUser,
                contentDescription = "",
                tint = PrimaryMain
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Identity verified",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color = PrimaryMain,
            )
        }
        Divider(
            modifier = Modifier
                .padding(vertical = 9.dp)
                .fillMaxWidth(),
            thickness = 1.dp,
            color = PrimaryBorder
        )
        UmkmBody()
    }
}

@Composable
fun UmkmBody() {
    val holidays = listOf("Jumat")
    val days = listOf("Senin", "Selasa", "Rabu", "Kamis", "Sabtu", "Minggu")

    Column {
        Text(
            text = "About",
            color = PrimaryMain,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text = "Lorem Ipsuml... ",
                color = PrimaryMain,
                fontSize = 16.sp
            )
            Text(
                text = "Read More",
                color = PrimaryMain,
                textDecoration = TextDecoration.Underline,
                fontSize = 16.sp,
            )
        }
        Spacer(modifier = Modifier.height(9.dp))
        Row(
            modifier = Modifier.padding(top = 9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Default.ShoppingBag,
                contentDescription = "",
                tint = PrimaryMain
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Souvenirs",
                color = PrimaryMain,
                fontSize = 16.sp
            )
        }
        Row(
            modifier = Modifier.padding(top = 9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Default.Message,
                contentDescription = "",
                tint = PrimaryMain
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "+62 838-1234-234",
                color = PrimaryMain,
                fontSize = 16.sp
            )
        }
        Divider(
            modifier = Modifier
                .padding(vertical = 9.dp)
                .fillMaxWidth(),
            thickness = 1.dp,
            color = PrimaryBorder
        )
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

        Text(
            text = "Closed Days",
            color = PrimaryMain,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700
        )
        holidays.forEach {
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
                    text = "Closed",
                    color = PrimaryMain,
                    fontSize = 16.sp
                )
            }
        }
    }
}