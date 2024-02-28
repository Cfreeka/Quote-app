package com.quotes.repositories

import com.quotes.data.QuoteDatabase
import com.quotes.data.QuoteEntity
import kotlinx.coroutines.flow.Flow

 class QuoteRepoImpl(private val database: QuoteDatabase): QuoteRepo {
    private val dao = database.dao()
    override suspend fun saveQuote(quote: QuoteEntity) = dao.saveQuote(quote)

    override suspend fun deleteQuote(quote: QuoteEntity) = dao.deleteQuote(quote)

    override suspend fun getQuotes(): Flow<List<QuoteEntity>> = dao.getQuotes()
}