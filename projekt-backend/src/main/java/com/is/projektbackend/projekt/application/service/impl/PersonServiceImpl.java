package com.is.projektbackend.projekt.application.service.impl;

import com.is.projektbackend.projekt.application.model.Person;
import com.is.projektbackend.projekt.application.repository.PersonRepository;
import com.is.projektbackend.projekt.application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Optional<Person> getById(Integer id) {
        return personRepository.findById(id);
    }

}
