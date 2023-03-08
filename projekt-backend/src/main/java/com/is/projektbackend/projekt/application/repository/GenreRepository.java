package com.is.projektbackend.projekt.application.repository;

import com.is.projektbackend.projekt.application.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findById(Integer id);

    Optional<Genre> findByGenreNameIgnoreCase(String genreName);

    Genre getGenreByGenreNameIgnoreCase(String name);
}
