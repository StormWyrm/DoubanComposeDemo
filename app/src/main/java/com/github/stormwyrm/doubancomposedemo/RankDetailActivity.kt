package com.github.stormwyrm.doubancomposedemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.github.stormwyrm.doubancomposedemo.ui.screens.rank.RankDetailScreen
import com.github.stormwyrm.doubancomposedemo.ui.theme.DoubanComposeDemoTheme
import com.google.accompanist.insets.ProvideWindowInsets

class RankDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            RankDetailPage()
        }
    }

    companion object {
        fun navigate(context: Context) {
            context.startActivity(Intent(context, RankDetailActivity::class.java))
        }
    }
}

@Composable
fun RankDetailPage() {
    DoubanComposeDemoTheme {
        ProvideWindowInsets {
            RankDetailScreen()
        }
    }
}