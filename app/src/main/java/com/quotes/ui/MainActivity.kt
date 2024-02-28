package com.quotes.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.quotes.screens.HomeScreen
import com.quotes.screens.Nav
import com.quotes.ui.theme.QuotesTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotesTheme {
              Nav()


            }
        }
    }

}

