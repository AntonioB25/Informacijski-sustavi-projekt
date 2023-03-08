package com.is.projektbackend.projekt.application.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class LendingResponseDto {

    @NotNull
    private Integer id;
    @NotNull
    private Integer bookId;
    @NotEmpty
    private String author;
    @NotEmpty
    private String bookName;
    @NotNull
    private Integer memberId;
    @NotEmpty
    private String memberName;
    @NotNull
    private LocalDate lendingDate;
    @NotNull
    private LocalDate returnDate;

    public LendingResponseDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDate getLendingDate() {
        return lendingDate;
    }

    public void setLendingDate(LocalDate lendingDate) {
        this.lendingDate = lendingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

}
