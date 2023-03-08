package com.is.projektbackend.projekt.application.repository;

import com.is.projektbackend.projekt.application.model.Book;
import com.is.projektbackend.projekt.application.model.BookSection;
import com.is.projektbackend.projekt.application.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAll();

    Optional<Book> findById(Integer id);

    List<Book> findAllByAuthorContainingIgnoreCase(String author);

    List<Book> findAllByGenre(Genre genre);

    List<Book> findAllBySection(BookSection section);

    List<Book> findAllByMemberId(Integer memberId);


    @Procedure
    List<Book> GET_SEARCHED_BOOKS(String p1);

}
