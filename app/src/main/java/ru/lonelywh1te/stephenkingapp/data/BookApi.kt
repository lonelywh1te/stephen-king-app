package ru.lonelywh1te.stephenkingapp.data

import retrofit2.Response
import retrofit2.http.GET
import ru.lonelywh1te.stephenkingapp.data.dto.DataResponse

interface BookApi {

    @GET("books")
    suspend fun getBooks(): Response<DataResponse>

}