package com.quotes.repositories

import com.quotes.data.QuoteEntity

object QuoteData {

    val quotes = listOf(
        QuoteEntity(0,"The only way to do great work is to love what you do.", "Steve Jobs"),
        QuoteEntity(1,"Believe you can and you're halfway there.", "Theodore Roosevelt"),
        QuoteEntity(2,"It does not matter how slowly you go as long as you do not stop.", "Confucius"),
        QuoteEntity(3,
            " Success is not final, failure is not fatal: It is the courage to continue that counts.",
            "Winston Churchill"
        ),

        QuoteEntity(4,
            "The only limit to our realization of tomorrow will be our doubts of today.",
            "Franklin D. Roosevelt"
        ),
        QuoteEntity(5,"Don't watch the clock; do what it does. Keep going.", "Sam Levenson"),
        QuoteEntity(6,"The best way to predict the future is to create it.", "Peter Drucker"),
        QuoteEntity(7,
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it.",
            "Jordan Belfort"
        ),
        QuoteEntity(8,"In order to succeed, we must first believe that we can.", "Nikos Kazantzakis"),
        QuoteEntity(9,"You are never too old to set another goal or to dream a new dream.", "C.S. Lewis"),
        QuoteEntity(10,
            "Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.",
            "Thomas A. Edison"
        )

    )

}