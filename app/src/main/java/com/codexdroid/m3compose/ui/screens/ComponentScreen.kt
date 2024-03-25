package com.codexdroid.m3compose.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codexdroid.m3compose.R
import com.codexdroid.m3compose.ui.activities.ScreenViewModel
import com.codexdroid.m3compose.ui.theme.Blue20
import com.codexdroid.m3compose.ui.theme.Blue80
import com.codexdroid.m3compose.ui.utils.ComponentData
import com.codexdroid.m3compose.ui.utils.componentsList
import com.codexdroid.m3compose.ui.utils.fontMontserrat
import com.codexdroid.m3compose.ui.utils.fontMontserratBold


@Preview
@Composable
fun PreviewComponent() {
    ComponentScreen({},viewModel())
}

@Composable
fun ComponentScreen(
    onCardClicked: (ComponentData) -> Unit
    ,screenViewModel: ScreenViewModel,
    modifier: Modifier = Modifier) {

    var searchField by remember { mutableStateOf("") }

    Column(modifier = modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(10.dp)) {

        OutlinedTextField (
            value = searchField,
            onValueChange = { searchField = it },
            label = { Text(
                text = stringResource(id = R.string.str_search),
                fontFamily = fontMontserrat
            )},
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.str_search))
            }, shape = RoundedCornerShape(10.dp),

            colors = OutlinedTextFieldDefaults.colors (
                focusedBorderColor = Blue80,
                focusedLeadingIconColor = Blue80,
                focusedLabelColor = Blue80,

                unfocusedBorderColor = Blue20,
                unfocusedLeadingIconColor = Blue20,
                unfocusedPlaceholderColor = Blue20,
                unfocusedLabelColor = Blue20,

                cursorColor = Blue80
            ), modifier = modifier.fillMaxWidth()
        )


        if (searchField.isNotEmpty()) {
            Text(text = "if(!matched) show all",
                fontFamily = fontMontserrat)
        }



        val filteredList = componentsList.filter {
            stringResource(id = it.componentName).lowercase().contains(searchField.lowercase())
        }

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {

            this.items(filteredList.ifEmpty { componentsList }) {
                ComponentCard(onCardClicked = {compData ->
                    screenViewModel.setSelectedComponent(compData)
                    onCardClicked(compData)
                },it)
            }
        }
    }
}

@Composable
fun ComponentCard(onCardClicked: (ComponentData) -> Unit, component: ComponentData, modifier: Modifier = Modifier) {

    Card(modifier = modifier
        .fillMaxWidth()
        .padding(6.dp)
        .clickable { onCardClicked(component) }
        .background(color = Blue20, shape = RoundedCornerShape(8.dp))
        .padding(horizontal = 16.dp, vertical = 20.dp),
        colors = CardDefaults.cardColors(containerColor = Blue20)) {

        Text(
            text = stringResource(id = component.componentName),
            fontFamily = fontMontserrat,
            fontSize = 10.sp,
            color = Blue80
        )
    }
}