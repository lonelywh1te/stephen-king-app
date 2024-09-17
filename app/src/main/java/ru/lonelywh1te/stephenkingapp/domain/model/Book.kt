package ru.lonelywh1te.stephenkingapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val title: String,
    val year: Int,
): Parcelable
