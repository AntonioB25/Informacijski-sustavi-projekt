package com.is.projektbackend.projekt.application.repository;

import com.is.projektbackend.projekt.application.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findById(Integer id);
    Boolean existsByEmail(String email);
}
