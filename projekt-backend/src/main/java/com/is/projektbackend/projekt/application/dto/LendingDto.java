package com.is.projektbackend.projekt.application.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LendingDto {
    @NotNull
    private Integer   bookId;
    @NotNull
    private Integer   memberId;

    public LendingDto(Integer bookId, Integer memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

}
