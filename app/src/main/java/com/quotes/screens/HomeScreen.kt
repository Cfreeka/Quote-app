package com.quotes.screens


import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.quotes.R
import com.quotes.repositories.QuoteData
import com.quotes.data.QuoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: QuoteViewModel = viewModel(),
) {
    val currentQuote = remember {
        mutableStateOf(Pair("", ""))
    }

    fun randomQuotes() {
        val randomQuoteIndex = Random.nextInt(QuoteData.quotes.size)
        val randomQuote = QuoteData.quotes[randomQuoteIndex]
        currentQuote.value = Pair(randomQuote.QuoteText, randomQuote.QuoteAuthor)
    }

    if (currentQuote.value.first.isEmpty()) {
        randomQuotes()
    }
    suspend fun saveQuote() {

        val quoteText = currentQuote.value.first
        val quoteAuthor = currentQuote.value.second
        viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.saveQuote(QuoteEntity(QuoteText = quoteText, QuoteAuthor = quoteAuthor))

        }

    }
    val context = LocalContext.current
    fun sendQuote() {


        val message = "${currentQuote.value.first} -${currentQuote.value.second} "
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
            `package` = "com.whatsapp"
        }
        try {
            context.startActivity(sendIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Whatsapp is not downloaded", Toast.LENGTH_LONG).show()
        }
    }





    Scaffold(
        containerColor = Color.White,
        topBar = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Quotes",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 45.sp,
                    fontFamily = FontFamily.Cursive,
                    modifier = Modifier.padding(start = 10.dp)
                )


                IconButton(
                    onClick = {
                        navController.navigate("Favourite_Screen") {
                            launchSingleTop = true
                        }
                    },

                    ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_save_icon),
                        contentDescription = "Favorite quotes",
                        tint = Color.White
                    )
                }


            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { randomQuotes() },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Next quote")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                    modifier = Modifier
                        .height(480.dp)
                        .width(320.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {


                        Column() {
                            Icon(
                                modifier = Modifier
                                    .size(70.dp)
                                    .padding(5.dp),
                                painter = painterResource(id = R.drawable.ic_quotes_icon),
                                contentDescription = "Quotes",
                                tint = Color.White
                            )
                            Text(
                                text = currentQuote.value.first,
                                fontSize = 33.sp,
                                fontFamily = FontFamily.Cursive,
                                color = MaterialTheme.colorScheme.secondary,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(16.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(45.dp)
                                        .padding(5.dp),
                                    painter = painterResource(id = R.drawable.ic_closing_icon),
                                    contentDescription = "Quotes",
                                    tint = Color.White
                                )

                            }
                        }



                        Text(
                            text = "- ${currentQuote.value.second}",
                            textAlign = TextAlign.End,
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Cursive,
                            modifier = Modifier
                                .padding(bottom = 20.dp, end = 20.dp)
                                .align(Alignment.BottomEnd)


                        )

                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    IconButton(onClick = {
                        viewModel.viewModelScope.launch {
                            saveQuote()

                        }
                        Toast.makeText(context, "Quote saved successfully", Toast.LENGTH_SHORT)
                            .show()


                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_saving_icon),
                            contentDescription = "Save Quote",
                            modifier = Modifier.size(40.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    IconButton(onClick = { sendQuote() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_send_icon),
                            contentDescription = "Save Quote",
                            modifier = Modifier.size(40.dp),
                            tint = MaterialTheme.colorScheme.primary

                        )
                    }
                }
            }

        }

    }

}

