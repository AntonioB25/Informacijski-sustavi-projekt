package com.is.projektbackend.projekt.application.service;

import com.is.projektbackend.projekt.application.dto.LendingDto;
import com.is.projektbackend.projekt.application.dto.LendingResponseDto;
import com.is.projektbackend.projekt.application.model.Book;
import com.is.projektbackend.projekt.application.model.Lending;

import java.util.List;

public interface LendingService {

    Lending lendBook(LendingDto lendingDto);

    List<LendingResponseDto> getLendedBooks();
    List<LendingResponseDto> getUserLendedBooks(Integer memberId);

    Boolean returnBook(Integer lendingId);
}
