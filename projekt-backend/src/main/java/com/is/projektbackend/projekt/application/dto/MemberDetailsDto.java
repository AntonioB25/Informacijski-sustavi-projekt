package com.is.projektbackend.projekt.application.dto;

import java.util.List;

public class MemberDetailsDto {

    private Integer memberId;
    private String  nameLastName;
    private String email;
    private Integer numberOfBorrowedBooks;
    private String membershipType;
    private Integer activity;
    private List<BookDto> borrowedBooks;

    public MemberDetailsDto(){

    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getNameLastName() {
        return nameLastName;
    }

    public void setNameLastName(String nameLastName) {
        this.nameLastName = nameLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumberOfBorrowedBooks() {
        return numberOfBorrowedBooks;
    }

    public void setNumberOfBorrowedBooks(Integer numberOfBorrowedBooks) {
        this.numberOfBorrowedBooks = numberOfBorrowedBooks;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public List<BookDto> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<BookDto> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

}
