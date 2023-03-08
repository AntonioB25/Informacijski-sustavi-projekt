package com.is.projektbackend.projekt.application.repository;

import com.is.projektbackend.projekt.application.model.BookState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookStateRepository extends JpaRepository<BookState, Integer> {
    /**
     *  Get book state by id
     */
    Optional<BookState> findById(Integer id);
}
