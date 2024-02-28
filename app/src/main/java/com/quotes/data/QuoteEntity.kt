package com.quotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val QuoteText: String,
    val QuoteAuthor: String
)