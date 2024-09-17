package ru.lonelywh1te.stephenkingapp.data.dto

import com.google.gson.annotations.SerializedName

data class BookDto (
    val id: Int,
    @SerializedName("Year") val year: Int,
    @SerializedName("Title") val title: String,
    val handle: String,
    @SerializedName("Publisher") val publisher: String,
    @SerializedName("ISBN") val isbn: String,
    @SerializedName("Pages") val pages: Int,
    @SerializedName("Notes") val notes: List<String>,
    @SerializedName("created_at") val createdAt: String,
    val villains: List<Villain>
)