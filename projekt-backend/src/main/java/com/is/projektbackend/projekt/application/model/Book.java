package com.is.projektbackend.projekt.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

    public Book() {
    }

    public Book(String author, String bookName, Integer issueYear, String publisher, Integer receiptYear) {
        this.author = author;
        this.bookName = bookName;
        this.issueYear = issueYear;
        this.publisher = publisher;
        this.receiptYear = receiptYear;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    private Integer id;

    @Column(name = "author", length = 50)
    private String author;

    @Column(name = "book_name", nullable = false, length = 50)
    private String bookName;

    @Column(name = "issue_year")
    private Integer issueYear;

    @Column(name = "publisher", length = 50)
    private String publisher;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private BookState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    private BookSection section;

    @Column(name = "receipt_year")
    private Integer receiptYear;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(Integer issueYear) {
        this.issueYear = issueYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public BookState getState() {
        return state;
    }

    public void setState(BookState state) {
        this.state = state;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public BookSection getSection() {
        return section;
    }

    public void setSection(BookSection section) {
        this.section = section;
    }

    public Integer getReceiptYear() {
        return receiptYear;
    }

    public void setReceiptYear(Integer receiptYear) {
        this.receiptYear = receiptYear;
    }

}