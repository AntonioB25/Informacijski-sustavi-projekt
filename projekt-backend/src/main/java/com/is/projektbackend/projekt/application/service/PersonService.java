package com.is.projektbackend.projekt.application.service;

import com.is.projektbackend.projekt.application.model.Person;

import java.util.Optional;

public interface PersonService {

    Optional<Person> getById(Integer id);

}
