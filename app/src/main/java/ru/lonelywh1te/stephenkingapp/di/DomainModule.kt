package ru.lonelywh1te.stephenkingapp.di

import org.koin.dsl.module
import ru.lonelywh1te.stephenkingapp.domain.usecase.GetBooksPublishedAfterYearUseCase

val domainModule = module {
    factory<GetBooksPublishedAfterYearUseCase> {
        GetBooksPublishedAfterYearUseCase(repository = get())
    }
}