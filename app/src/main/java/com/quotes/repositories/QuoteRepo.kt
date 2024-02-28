package com.quotes.repositories

import com.quotes.data.QuoteEntity
import kotlinx.coroutines.flow.Flow

interface QuoteRepo {
    suspend fun saveQuote(quote: QuoteEntity)
    suspend fun deleteQuote(quote: QuoteEntity)
    suspend fun getQuotes(): Flow<List<QuoteEntity>>
}