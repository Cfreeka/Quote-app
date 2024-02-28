package com.quotes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Insert
    fun saveQuote(quote: QuoteEntity)

    @Delete
    fun deleteQuote(quote: QuoteEntity)

    @Query("SELECT * FROM quotes")
    fun getQuotes(): Flow<List<QuoteEntity>>
}