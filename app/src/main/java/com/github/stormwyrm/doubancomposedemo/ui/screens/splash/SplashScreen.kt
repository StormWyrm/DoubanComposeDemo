package com.github.stormwyrm.doubancomposedemo.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.stormwyrm.doubancomposedemo.R
import com.github.stormwyrm.doubancomposedemo.ui.theme.DoubanComposeDemoTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.text.SimpleDateFormat
import java.util.*

private const val TIME = 3

@Composable
fun SplashScreen(onSplashCompleted: () -> Unit) {
    rememberSystemUiController().setStatusBarColor(Color.Transparent, darkIcons = true)
    Surface(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(16.dp)
        ) {

            val time = mutableStateOf(TIME)

            LaunchedEffect(Unit) {
                flow {
                    for (index in TIME downTo 0) {
                        emit(index)
                        delay(1000L)
                    }
                }.flowOn(Dispatchers.Main)
                    .onCompletion { onSplashCompleted() }
                    .onEach { time.value = it }
                    .launchIn(this)
            }

            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp),
            ) {
                Text(
                    text = "${time.value}s",
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                )
            }

            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_smile),
                    contentDescription = "logo",
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = getCurTime(),
                    color = Color.Black,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "遇见你，真美好",
                    color = Color.Black,
                    style = MaterialTheme.typography.body1
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.padding(start = 3.dp, end = 3.dp))

                Text(text = "豆瓣", color = Color.Black, style = MaterialTheme.typography.h4)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplashScreenPrew() {
    DoubanComposeDemoTheme {
        SplashScreen {

        }
    }
}

private fun getCurTime() = try {
    val time = System.currentTimeMillis()
    val date = Date(time)
    val format = SimpleDateFormat("yyyy年MM月dd日 EEEE")
    format.format(date)
} catch (e: Exception) {
    "2021年8月22日 星期日"
}