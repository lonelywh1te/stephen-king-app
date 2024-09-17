package ru.lonelywh1te.stephenkingapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lonelywh1te.stephenkingapp.domain.model.Book
import ru.lonelywh1te.stephenkingapp.domain.usecase.GetBooksPublishedAfterYearUseCase

private const val LOG_TAG = "MainActivityViewModel"

class MainActivityViewModel(
    private val getBooksPublishedAfterYearUseCase: GetBooksPublishedAfterYearUseCase
): ViewModel() {
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> get() = _books

    fun getBooksAfterYear(year: Int) {
        Log.d(LOG_TAG, "books loading")
        viewModelScope.launch {
            val books = getBooksPublishedAfterYearUseCase.execute(year)
            _books.postValue(books)
        }
    }
}