package com.is.projektbackend.projekt.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Genre {

    public Genre() {
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false)
    private Integer id;

    @Column(name = "genre_name", length = 50)
    private String genreName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return "Genre{" +
            "id=" + id +
            ", genreName='" + genreName + '\'' +
            '}';
    }

}