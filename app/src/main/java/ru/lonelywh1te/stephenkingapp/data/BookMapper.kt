package ru.lonelywh1te.stephenkingapp.data

import ru.lonelywh1te.stephenkingapp.data.dto.BookDto
import ru.lonelywh1te.stephenkingapp.domain.model.Book

class BookMapper {
    fun bookDtoToBook(bookDto: BookDto): Book {
        return Book(
            id = bookDto.id,
            title = bookDto.title,
            year = bookDto.year
        )
    }
}