package com.quotes.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quotes.data.QuoteEntity
import com.quotes.repositories.QuoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class QuoteViewModel : ViewModel(), KoinComponent {
    private val repo: QuoteRepo by inject()
    private val _quotes: MutableStateFlow<List<QuoteEntity>> = MutableStateFlow(emptyList())
    var quotes = _quotes.asStateFlow()

    init {
        getQuotes()
    }

    fun saveQuote(quote: QuoteEntity) {
        viewModelScope.launch {
            repo.saveQuote(quote)
            _quotes.value += quote
        }
    }

     private fun getQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getQuotes().collect {
                _quotes.update { it }
            }
        }

    }


    fun deleteQuote(quote: QuoteEntity) {
        viewModelScope.launch {
            repo.deleteQuote(quote)
        }
    }


}