package com.capstone.chillgoapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capstone.chillgoapp.R
import com.capstone.chillgoapp.ui.theme.ChillGoAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreSearch(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchBar(
            query = "",
            onQueryChange = {},
            onSearch = {},
            active = false,
            onActiveChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            placeholder = {
                Text(stringResource(R.string.placeholder_search))
            },
            shape = MaterialTheme.shapes.large,
            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 48.dp)
        ) {}
        Spacer(modifier = Modifier.width(16.dp))
        Surface(
            modifier = Modifier.clip(RoundedCornerShape(10.dp)),
            color = Color(0X80C7CEBE)
        ) {
            Image(
                modifier = Modifier
                    .padding(13.dp)
                    .size(24.dp),
                painter = painterResource(id = R.drawable.adjust),
                contentDescription = "Tune"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoreSearchPreview() {
    ChillGoAppTheme {
        MoreSearch()
    }
}