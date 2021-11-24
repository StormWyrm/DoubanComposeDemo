package com.github.stormwyrm.doubancomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import com.github.stormwyrm.doubancomposedemo.ui.screens.home.HomeScreen
import com.github.stormwyrm.doubancomposedemo.ui.screens.splash.SplashScreen
import com.github.stormwyrm.doubancomposedemo.ui.theme.DoubanComposeDemoTheme
import com.google.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
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
        ProvideWindowInsets {
            val (appState, setAppState) = remember { mutableStateOf(AppState.Splash) }

            when (appState) {
                AppState.Splash -> {
                    SplashScreen { setAppState(AppState.Home) }
                }

                AppState.Home -> {
                    HomeScreen()
                }
            }
        }

    }
}

