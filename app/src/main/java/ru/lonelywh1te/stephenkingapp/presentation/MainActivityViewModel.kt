package ru.lonelywh1te.stephenkingapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.lonelywh1te.stephenkingapp.domain.model.Book
import ru.lonelywh1te.stephenkingapp.domain.model.Result
import ru.lonelywh1te.stephenkingapp.domain.usecase.GetBooksPublishedAfterYearUseCase

private const val LOG_TAG = "MainActivityViewModel"

class MainActivityViewModel(
    private val getBooksPublishedAfterYearUseCase: GetBooksPublishedAfterYearUseCase
): ViewModel() {
    private val _books = MutableLiveData<Result<List<Book>>>()
    val books: LiveData<Result<List<Book>>> get() = _books

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getBooksAfterYear(year: Int) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            _books.postValue(getBooksPublishedAfterYearUseCase.execute(year))
            _isLoading.postValue(false)
        }
    }
}