package com.codexdroid.m3compose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codexdroid.m3compose.R
import com.codexdroid.m3compose.ui.theme.Blue20
import com.codexdroid.m3compose.ui.theme.Blue80
import com.codexdroid.m3compose.ui.theme.Gray63
import com.codexdroid.m3compose.ui.utils.AppConstants
import com.codexdroid.m3compose.ui.utils.DevData
import com.codexdroid.m3compose.ui.utils.devDetails
import com.codexdroid.m3compose.ui.utils.fontMontserrat
import com.codexdroid.m3compose.ui.utils.fontMontserratBold
import com.codexdroid.m3compose.ui.utils.purposes

@Preview
@Composable
fun HomePreview() {
    HomeScreen({},{})
}

@Composable
fun HomeScreen(
    onViewClicked: () -> Unit,
    onPlayGroundClicked: () -> Unit,
    modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Box(modifier = modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        Column {

            Card(modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Blue20),
                shape = RoundedCornerShape(20.dp)) {

                Text(
                    text = stringResource(id = R.string.str_purpose),
                    modifier = modifier
                        .padding(10.dp)
                        .background(
                            color = Blue80,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 30.dp, vertical = 12.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = fontMontserrat
                )


                LazyColumn (
                    modifier = modifier.padding(10.dp,10.dp)){
                    this.items (purposes) {
                        MyPurpose(purpose = it)
                    }
                }

                LazyRow(modifier = modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    this.items(devDetails) {
                        DevDetailsRow(it)
                    }
                }
            }

            Card(modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Blue20),
                shape = RoundedCornerShape(16.dp)) {

                Column(modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp)) {
                    Text(
                        text = stringResource(id = R.string.str_components),
                        fontFamily = fontMontserratBold,
                        fontSize = 24.sp,
                        modifier = modifier)

                    Text(
                        text = AppConstants.URLS.COMPOSE_REF,
                        fontFamily = fontMontserrat,
                        color = Gray63,
                        modifier = modifier)

                    Button(
                        onClick = { onViewClicked() },
                        modifier = modifier.align(alignment = Alignment.End),
                        colors = ButtonDefaults.buttonColors(containerColor = Blue80)) {
                        Text (
                            text = stringResource(R.string.str_view),
                            fontFamily = fontMontserrat,
                            fontWeight = FontWeight.W700
                        )
                    }
                }
            }

            OutlinedButton(onClick = { onPlayGroundClicked() },
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                Text(
                    text = "Goto Playground",
                    fontFamily = fontMontserrat,
                    color = Blue80)
            }
        }

        Column(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.inspired_from),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontFamily = fontMontserrat
            )

            Text(
                text = stringResource(R.string.m3and_its_App),
                fontSize = 16.sp,
                fontFamily = fontMontserrat,
                fontWeight = FontWeight.W600
            )

            val manager = context.packageManager
            val version = manager.getPackageInfo(context.packageName,0).versionName
            Text(
                text = "Beginner Version: $version",
                fontSize = 12.sp,
                fontFamily = fontMontserrat,
                fontWeight = FontWeight.W600
            )
        }
    }
}

@Composable
fun MyPurpose(purpose:Int, modifier: Modifier = Modifier) {

    //Todo: Change to default font-family
    Text(
        text = stringResource(id = purpose),
        fontSize = 14.sp,
        style = TextStyle(
            fontFamily = fontMontserrat
        )
    )
    Spacer(modifier = modifier.padding(top = 10.dp))
}

@Composable
fun DevDetailsRow(devDetail: DevData, modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val uriHandler = LocalUriHandler.current

    Column (modifier = Modifier
        .padding(6.dp)
        .background(
            color = Color.White,
            shape = RoundedCornerShape(10.dp)
        )
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            role = Role.Button
        ) {
            uriHandler.openUri(devDetail.url)
        }
        .padding(8.dp)
        .size(80.dp, 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Icon(painter = painterResource(id = devDetail.icon), modifier = modifier.size(24.dp),
            contentDescription = stringResource(id = devDetail.text))

        Text(
            text = stringResource(id = devDetail.text),
            fontFamily = fontMontserrat,
            fontSize = 12.sp,
            color = Color.Blue,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = modifier.padding(start = 6.dp, end = 6.dp)
        )
    }
}