package ru.lonelywh1te.stephenkingapp.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.lonelywh1te.stephenkingapp.presentation.MainActivityViewModel

val appModule = module {
    viewModel<MainActivityViewModel> {
        MainActivityViewModel(
            getBooksPublishedAfterYearUseCase = get()
        )
    }
}