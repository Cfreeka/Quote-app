package com.quotes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quotes.R
import com.quotes.data.QuoteEntity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    navController: NavHostController,
    viewModel: QuoteViewModel = viewModel(),

    ) {

    val quotes by  remember { viewModel.quotes }.collectAsState()
    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(

                title = {
                    Text(
                        text = "Favourite Quotes",
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 35.sp,
                        fontFamily = FontFamily.Cursive,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Home_Screen") }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back",
                            tint = MaterialTheme.colorScheme.secondary
                        )

                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primary),

                )
        }

    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn() {



                items(quotes) { quote ->
                    QuoteItem(
                        quote = quote,
                        onDelete = {
                            viewModel.viewModelScope.launch {
                                viewModel.deleteQuote(quote)
                            }

                        }

                    )

                }
            }
        }


    }

}

@Composable
fun QuoteItem(quote: QuoteEntity, onDelete: () -> Unit) {
    Column(modifier = Modifier.padding(20.dp)) {
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
                        text = quote.QuoteText,
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
                    text = "- ${quote.QuoteAuthor}",
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
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onDelete() }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete quote")

            }
        }

    }

}