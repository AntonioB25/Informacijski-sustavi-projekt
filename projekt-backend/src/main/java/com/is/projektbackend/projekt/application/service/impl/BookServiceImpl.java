package com.is.projektbackend.projekt.application.service.impl;

import com.is.projektbackend.projekt.application.dto.BookDto;
import com.is.projektbackend.projekt.application.exceptions.SectionNotFoundException;
import com.is.projektbackend.projekt.application.model.Book;
import com.is.projektbackend.projekt.application.model.BookSection;
import com.is.projektbackend.projekt.application.model.BookState;
import com.is.projektbackend.projekt.application.model.Genre;
import com.is.projektbackend.projekt.application.model.Lending;
import com.is.projektbackend.projekt.application.model.Reservation;
import com.is.projektbackend.projekt.application.repository.BookRepository;
import com.is.projektbackend.projekt.application.repository.BookStateRepository;
import com.is.projektbackend.projekt.application.repository.GenreRepository;
import com.is.projektbackend.projekt.application.repository.LendingRepository;
import com.is.projektbackend.projekt.application.repository.ReservationRepository;
import com.is.projektbackend.projekt.application.repository.SectionRepository;
import com.is.projektbackend.projekt.application.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final int BOOK_STATE_FREE_ID = 1;

    private BookRepository      bookRepository;
    private GenreRepository     genreRepository;
    private SectionRepository   sectionRepository;
    private BookStateRepository bookStateRepository;
    private LendingRepository lendingRepository;
    private ReservationRepository reservationRepository;
    @Autowired
    private ModelMapper         modelMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository,
        SectionRepository sectionRepository, BookStateRepository bookStateRepository,
        LendingRepository lendingRepository,
        ReservationRepository reservationRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.sectionRepository = sectionRepository;
        this.bookStateRepository = bookStateRepository;
        this.lendingRepository = lendingRepository;
        this.reservationRepository = reservationRepository;
    }


    @Override
    public List<BookDto> listAll() {
        return bookRepository.findAll().stream().map(this::convertBookToDto).toList();
    }

    @Override
    public BookDto getBookById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return null;
        }

        return convertBookToDto(book.get());
    }

    @Override
    public BookDto addBook(BookDto bookDto) throws SectionNotFoundException {
        Book newBook = bookDto.toBook();
        BookState bookState = bookStateRepository.getById(BOOK_STATE_FREE_ID); // get state free, state_name=slobodna

        Genre genre = genreRepository.getGenreByGenreNameIgnoreCase(bookDto.getGenreName());
        if (genre == null) {
            genre = genreRepository.save(new Genre(bookDto.getGenreName()));
        }

        Optional<BookSection> section = sectionRepository.findById(Integer.parseInt(bookDto.getSectionName()));
        if (section.isEmpty()) {
            throw new SectionNotFoundException("No section with given id");
        }

        newBook.setGenre(genre);              // set genre for book
        newBook.setSection(section.get());    // set section for book
        newBook.setState(bookState);
        return convertBookToDto(bookRepository.save(newBook));
    }

    @Override
    public List<BookDto> getBooksByAuthor(String author) {
        return null;
    }

    @Override
    public List<BookDto> getBooksByGenre(Integer genreId) {
        Optional<Genre> genre = genreRepository.findById(genreId);
        return genre.map(value -> bookRepository.findAllByGenre(value).stream().map(this::convertBookToDto).toList())
            .orElse(Collections.emptyList());
    }

    @Override
    public List<BookDto> getBooksBySection(BookSection section) {
        return null;
    }

    @Override
    public Boolean removeBook(Integer bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book == null) {
            return false;
        }
        List<Lending> lendings = lendingRepository.findAllByBookId(bookId);
        List<Reservation> reservations = reservationRepository.findAllByBookId(bookId);

        System.out.println("POSUDBE: " + lendings);
        System.out.println("REZERVACIJE: " + reservations);
        if(!(lendings.isEmpty() && reservations.isEmpty())){
            return false;
        }

        System.out.println("PRIJE BRISANJA SAM");
        bookRepository.delete(book);
        return true;
    }

    @Override
    public List<BookDto> getSearchedBooks(String searchQuery) {
        String preparedQuery = prepareQuery(searchQuery.toLowerCase());
        System.out.println("Prepared: " + preparedQuery);
        return bookRepository.GET_SEARCHED_BOOKS(preparedQuery).stream().map(this::convertBookToDto).toList();
    }

    @Override
    public BookDto editBook(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getId()).get();
        book.setAuthor(bookDto.getAuthor());
        book.setBookName(bookDto.getBookName());
        book.setIssueYear(bookDto.getIssueYear());
        book.setPublisher(bookDto.getPublisher());
        book.setReceiptYear(bookDto.getReceiptYear());
        return null;
    }

    @Override
    public Boolean isBookFree(Integer bookId) {
        Book book = bookRepository.findById(bookId).get();
        Integer bookStateId = book.getState().getId();

        return bookStateId == BOOK_STATE_FREE_ID;
    }

    private String prepareQuery(String query) {
        return query.replaceAll("\\s+","|");
    }

    private Book convertBookDtoToBook(BookDto bookDto) {
//        Book book = modelMapper.map(bookDto,Book.class);
//        Genre genre = genreRepository.getGenreByGenreNameIgnoreCase(bookDto.getGenreName())
//        book.

        return null;
    }

    private BookDto convertBookToDto(Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        bookDto.setGenreName(book.getGenre().getGenreName());
        bookDto.setSectionName(book.getSection().getSectionName());
        //mozda dodat za stanje
        return bookDto;
    }



}
