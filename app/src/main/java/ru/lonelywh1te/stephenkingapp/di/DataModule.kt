package ru.lonelywh1te.stephenkingapp.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.lonelywh1te.stephenkingapp.data.BookApi
import ru.lonelywh1te.stephenkingapp.data.repository.BookRepositoryImpl
import ru.lonelywh1te.stephenkingapp.domain.repository.IBookRepository

private const val BASE_URL = "https://stephen-king-api.onrender.com/api/"

val dataModule = module {
    single<BookApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<BookApi>()
    }

    single<IBookRepository> {
        BookRepositoryImpl(api = get())
    }
}