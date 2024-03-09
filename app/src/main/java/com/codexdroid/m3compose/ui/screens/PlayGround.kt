package com.codexdroid.m3compose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codexdroid.m3compose.R
import com.codexdroid.m3compose.ui.theme.Blue20
import com.codexdroid.m3compose.ui.theme.Blue40

@Preview
@Composable
fun PlayGroundPreview() {
    PlayGroundScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayGroundScreen(modifier: Modifier = Modifier) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val scrollBehavior2 = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val scrollBehavior3 = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())


    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text(text = "TopAppBar Title") },
                navigationIcon = {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null)
                },
                actions = {

                    //Note: As Action you can show anything,
                    // it is completely customizable

                    Text(text = "Search")

                    Image(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                        modifier = modifier.padding(8.dp))
                },
                colors = TopAppBarDefaults
                    .topAppBarColors()
                    .copy(containerColor = Color.Yellow),
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .padding(10.dp)) {

            Text(text = "Text as a Action", modifier.padding(10.dp))
            Text(text = "Icon as a Action", modifier.padding(10.dp))
            Text(text = "Custom UI as a Action", modifier.padding(10.dp))
        }
    }
}