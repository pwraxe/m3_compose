package com.codexdroid.m3compose.ui.screens

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codexdroid.m3compose.ui.theme.Blue20
import com.codexdroid.m3compose.ui.theme.Blue80

@Preview
@Composable
fun PlayGroundPreview() {
    PlayGroundScreen()
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun PlayGroundScreen(modifier: Modifier = Modifier) {

    Column(modifier = modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        Card(modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp, end = 10.dp)) {
            CardData(modifier)
        }
        Text(text = "Default Card")

        Card(modifier = modifier
            .fillMaxWidth()
            .padding(start = 0.dp, top = 10.dp, end = 10.dp),
            onClick = {},
            enabled = true,
            shape = CutCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Yellow
            ), border = BorderStroke(width = 1.dp, Color.Black),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            CardData(modifier = modifier)
        }
        Text(text = "Default Card with Style")

        ElevatedCard(
            onClick = {},
            modifier = modifier.padding(
                start = 10.dp,
                top = 10.dp,
                end = 10.dp)) {
            CardData(modifier = modifier)
        }
        Text(text = "Elevated Card")

        OutlinedCard(
            onClick = {},
            modifier = modifier.padding(
                start = 10.dp,
                top = 10.dp,
                end = 10.dp)
            ) {
            CardData(modifier = modifier)
        }
        Text(text = "Outline Card")


    }
}

@Composable
fun CardData(modifier: Modifier) {
    Column (modifier = modifier.padding(10.dp)){
        Text(text = "This is Card")
        Text(text = "This is Content 1")
        Text(text = "This is Content 2")
        Text(text = "This is Content n")
        Text(text = "Like wise you can add anything")

    }
}