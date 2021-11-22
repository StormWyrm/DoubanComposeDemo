package com.github.stormwyrm.doubancomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.github.stormwyrm.doubancomposedemo.ui.screens.SplashScreen
import com.github.stormwyrm.doubancomposedemo.ui.theme.DoubanComposeDemoTheme
import com.google.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDoubanUI()
        }
    }
}

enum class AppState {
    Splash,
    Home
}

@Composable
fun ComposeDoubanUI() {
    DoubanComposeDemoTheme {
        ProvideWindowInsets() {
            var appState = remember { mutableStateOf(AppState.Splash) }

        }
    }
}
