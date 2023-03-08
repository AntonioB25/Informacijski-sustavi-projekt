package com.is.projektbackend.projekt.application.service.impl;

import com.is.projektbackend.projekt.application.dto.LendingDto;
import com.is.projektbackend.projekt.application.dto.LendingResponseDto;
import com.is.projektbackend.projekt.application.model.Book;
import com.is.projektbackend.projekt.application.model.Lending;
import com.is.projektbackend.projekt.application.model.Member;
import com.is.projektbackend.projekt.application.repository.BookRepository;
import com.is.projektbackend.projekt.application.repository.LendingRepository;
import com.is.projektbackend.projekt.application.repository.MemberRepository;
import com.is.projektbackend.projekt.application.service.LendingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LendingServiceImpl implements LendingService {

    private static final int               RETURN_PERIOD_IN_MONTHS = 1;
    private              LendingRepository lendingRepository;
    private              BookRepository    bookRepository;
    private              MemberRepository  memberRepository;

    public LendingServiceImpl(LendingRepository lendingRepository, BookRepository bookRepository,
        MemberRepository memberRepository) {
        this.lendingRepository = lendingRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public Lending lendBook(LendingDto lendingDto) {
        Book book = bookRepository.getById(lendingDto.getBookId());
        Member member = memberRepository.getById(lendingDto.getMemberId());
        member.borrowBook();
        LocalDate lendingDate = LocalDate.now();
        LocalDate returnDate = LocalDate.now().plusMonths(RETURN_PERIOD_IN_MONTHS);

        Lending newLending = new Lending();
        newLending.setBook(book);
        newLending.setMember(member);
        newLending.setLendingDate(lendingDate);
        newLending.setReturnDate(returnDate);
        return lendingRepository.save(newLending);
    }

    @Override
    public List<LendingResponseDto> getLendedBooks() {
        List<Lending> lendedBooks = lendingRepository.findAll();
        return lendedBooks.stream().map(this::lendingToLendingResponseDto).toList();
    }

    @Override
    public List<LendingResponseDto> getUserLendedBooks(Integer memberId) {
        List<Lending> userLendings = lendingRepository.findAllByMemberId(memberId);
        return userLendings.stream().map(this::lendingToLendingResponseDto).toList();
    }
    @Override
    public Boolean returnBook(Integer lendingId) {
        Lending lending = lendingRepository.findById(lendingId).orElse(null);
        if (lending == null) {
            return false;
        }
        lending.getMember().returnBook();   // decrease borrowed books number
        lendingRepository.delete(lending);
        return true;
    }

    private LendingResponseDto lendingToLendingResponseDto(Lending lending){
        LendingResponseDto lendingResponseDto = new LendingResponseDto();
        Book book = lending.getBook();
        Member member = lending.getMember();

        lendingResponseDto.setId(lending.getId());
        lendingResponseDto.setBookId(book.getId());
        lendingResponseDto.setBookName(book.getBookName());
        lendingResponseDto.setAuthor(book.getAuthor());
        lendingResponseDto.setMemberId(member.getId());
        lendingResponseDto.setMemberName(member.getPerson().getNameLastname());
        lendingResponseDto.setLendingDate(lending.getLendingDate());
        lendingResponseDto.setReturnDate(lending.getReturnDate());

        return lendingResponseDto;
    }



}
