package com.is.projektbackend.projekt.application.controller;

import com.is.projektbackend.projekt.application.dto.BookDto;
import com.is.projektbackend.projekt.application.exceptions.BookNotFoundException;
import com.is.projektbackend.projekt.application.exceptions.QueryNotValidException;
import com.is.projektbackend.projekt.application.service.BookService;
import com.is.projektbackend.projekt.application.util.BlackListWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private BookService  bookService;
    private List<String> words;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
        this.words = new ArrayList<>();
        words.add("select");
        words.add("from");
    }

    @GetMapping("/allBooks")
    public List<BookDto> getBooks() {
        return bookService.listAll();
    }

    @PostMapping("/admin/addBook")
    public BookDto addBook(@Valid @RequestBody BookDto newBookDto) {
        return bookService.addBook(newBookDto);
    }

    @DeleteMapping("/admin/removeBook")
    public ResponseEntity<String> removeBook(Integer bookId) {
        if (!bookService.removeBook(bookId)) {
            return ResponseEntity.badRequest().body("Book cannot be deleted: doesn't exist, is reserved or borrowed");
        }else{
            return ResponseEntity.ok("Book deletion recorded");
        }

    }

    @GetMapping(value = "", params = "bookId")
    public BookDto getBook(@RequestParam Integer bookId) {
        BookDto book = bookService.getBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("No book with given id");
        }
        return book;
    }

    @PostMapping("/editBook")
    public ResponseEntity<BookDto> editBook(@RequestBody BookDto bookDto) {
        BookDto updatedBook = bookService.editBook(bookDto);
        return ResponseEntity.ok().body(updatedBook);
    }

    //TODO: fix
    @RequestMapping(value = "", params = "genreId")
    public List<BookDto> getBooksByGenre(@RequestParam Integer genreId) {
        return bookService.getBooksByGenre(genreId);
    }

    @GetMapping("/search")
    @Transactional(readOnly = true)
    public List<BookDto> searchBooksByQuery(@Size(min = 5, max = 80) @RequestParam String query) {
        Boolean isValid = isQueryValid(query.toLowerCase());
        if (!isValid) {
            throw new QueryNotValidException("Invalid characters or words");
        }
        return bookService.getSearchedBooks(query);
    }

    private Boolean isQueryValid(String query) {

        boolean bool = query.chars().allMatch(
            c -> (Character.isDigit(c) || Character.isLetter(c) || c == ' ')); // can be letter, number or space
        if (!bool) {
            return false;
        }

        List<String> wordsList = Arrays.stream(query.split("\\s+")).toList();
        return !wordsList.containsAll(BlackListWords.getBlackListedWords());
    }

}
