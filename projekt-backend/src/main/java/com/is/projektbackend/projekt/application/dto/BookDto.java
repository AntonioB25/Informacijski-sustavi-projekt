package com.is.projektbackend.projekt.application.dto;

import com.is.projektbackend.projekt.application.model.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class BookDto {


    private Integer id;
    @NotNull(message = "Author is mandatory")
    private  String  author;
    @NotBlank
    private  String  bookName;
    @NotNull
    private  Integer issueYear;
    @NotBlank
    private  String publisher;
    @NotBlank
    private  String  genreName;
    @NotNull
    private  String  sectionName;
    @NotNull
    private  Integer receiptYear;


    public BookDto() {
    }

    public BookDto(String author, String bookName, Integer issueYear, String publisher, String genreName,
        String sectionName, Integer receiptYear) {
        this.author = author;
        this.bookName = bookName;
        this.issueYear = issueYear;
        this.publisher = publisher;
        this.genreName = genreName;
        this.sectionName = sectionName;
        this.receiptYear = receiptYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookName() {
        return bookName;
    }

    public Integer getIssueYear() {
        return issueYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getGenreName() {
        return genreName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public Integer getReceiptYear() {
        return receiptYear;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setIssueYear(Integer issueYear) {
        this.issueYear = issueYear;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public void setReceiptYear(Integer receiptYear) {
        this.receiptYear = receiptYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookDto entity = (BookDto) o;
        return Objects.equals(this.author, entity.author) &&
            Objects.equals(this.bookName, entity.bookName) &&
            Objects.equals(this.issueYear, entity.issueYear) &&
            Objects.equals(this.publisher, entity.publisher) &&
            Objects.equals(this.genreName, entity.genreName) &&
            Objects.equals(this.sectionName, entity.sectionName) &&
            Objects.equals(this.receiptYear, entity.receiptYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, bookName, issueYear, publisher, genreName, sectionName, receiptYear);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
            "author = " + author + ", " +
            "bookName = " + bookName + ", " +
            "issueYear = " + issueYear + ", " +
            "publisher = " + publisher + ", " +
            "genreGenreName = " + genreName + ", " +
            "sectionSectionName = " + sectionName + ", " +
            "receiptYear = " + receiptYear + ")";
    }

    public Book toBook(){
        return new Book(author,bookName,issueYear,publisher,receiptYear);
    }




}
