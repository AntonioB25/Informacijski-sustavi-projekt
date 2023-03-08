package com.is.projektbackend.projekt.application.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book_section")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookSection {
    public BookSection() {
    }

    public BookSection(String sectionName) {
        this.sectionName = sectionName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id", nullable = false)
    private Integer id;

    @Column(name = "section_name", length = 50)
    private String sectionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Override
    public String toString() {
        return "BookSection{" +
            "id=" + id +
            ", sectionName='" + sectionName + '\'' +
            '}';
    }

}