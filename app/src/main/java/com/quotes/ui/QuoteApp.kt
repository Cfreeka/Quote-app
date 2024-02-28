package com.quotes.ui

import android.app.Application
import androidx.room.Room
import com.quotes.data.QuoteDatabase
import com.quotes.repositories.QuoteRepo
import com.quotes.repositories.QuoteRepoImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class QuoteApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@QuoteApp)
            modules(databaseModule, repositoryModule)
        }

    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            QuoteDatabase :: class.java,
            "Quote_database"
        ).allowMainThreadQueries().build()
    }
}
val repositoryModule = module {
    single<QuoteRepo> {
        QuoteRepoImpl(get())
    }
}