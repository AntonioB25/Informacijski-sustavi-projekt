package com.is.projektbackend.projekt.application.controller;

import com.is.projektbackend.projekt.application.model.Person;
import com.is.projektbackend.projekt.application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/person")
@CrossOrigin
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/get/")
    public Person getTrip(@RequestParam Integer personId) {
        Optional<Person> person = personService.getById(personId);
        return person.orElse(null);
    }



}
