package com.is.projektbackend.projekt.application.service;

import com.is.projektbackend.projekt.application.dto.BookDto;
import com.is.projektbackend.projekt.application.exceptions.SectionNotFoundException;
import com.is.projektbackend.projekt.application.model.Book;
import com.is.projektbackend.projekt.application.model.BookSection;

import java.util.List;
import java.util.Optional;

public interface BookService {

    /**
     * Get all books
     */
    List<BookDto> listAll();

    /**
     * Get book by id
     */
    BookDto getBookById(Integer id);

    /**
     * Add book
     */
    BookDto addBook(BookDto bookDto) throws SectionNotFoundException;

    /**
     * Get all books by author
     */
    List<BookDto> getBooksByAuthor(String author);

    /**
     * Get all books by genre
     */
    List<BookDto> getBooksByGenre(Integer genreId);

    /**
     * Get all books by section
     */
    List<BookDto> getBooksBySection(BookSection section);

    Boolean removeBook(Integer bookId);

    List<BookDto> getSearchedBooks(String searchQuery);

    BookDto editBook(BookDto bookDto);

    Boolean isBookFree(Integer bookId);

}
