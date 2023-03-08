package com.is.projektbackend.projekt.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
    public Person() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private Integer id;

    @Column(name = "name_lastname", nullable = false, length = 50)
    private String nameLastname;

    @Column(name = "email", length = 50)
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameLastname() {
        return nameLastname;
    }

    public void setNameLastname(String nameLastname) {
        this.nameLastname = nameLastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}