package com.is.projektbackend.projekt.application.controller;

import com.is.projektbackend.projekt.application.dto.LendingDto;
import com.is.projektbackend.projekt.application.dto.LendingResponseDto;
import com.is.projektbackend.projekt.application.exceptions.BorrowedLimitException;
import com.is.projektbackend.projekt.application.exceptions.LendingNotFoundException;
import com.is.projektbackend.projekt.application.exceptions.MemberNotActiveException;
import com.is.projektbackend.projekt.application.exceptions.MemberNotFoundException;
import com.is.projektbackend.projekt.application.model.Lending;
import com.is.projektbackend.projekt.application.model.Reservation;
import com.is.projektbackend.projekt.application.service.BookService;
import com.is.projektbackend.projekt.application.service.LendingService;
import com.is.projektbackend.projekt.application.service.MemberService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lending")
@CrossOrigin
public class LendingController {

    private BookService bookService;
    private MemberService memberService;
    private LendingService lendingService;

    @Autowired
    public LendingController(BookService bookService, MemberService memberService, LendingService lendingService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.lendingService = lendingService;
    }


    @PostMapping("/admin/lendBook")
    public Lending lendBook(@RequestBody LendingDto lendingDto){
        if(!memberService.isMemberActive(lendingDto.getMemberId())){
            throw new MemberNotActiveException("Member not active, cannot lend book");
        }

        if(memberService.hasReachedMaximumBorrowedBooks(lendingDto.getMemberId())){
            throw new BorrowedLimitException("Member has reached maximum number borrowed books");
        }

        return lendingService.lendBook(lendingDto);
    }

    @DeleteMapping("/admin/returnBook")
    public ResponseEntity<String> returnBook(@RequestParam Integer lendingId){
        if(!lendingService.returnBook(lendingId)){
            throw new LendingNotFoundException("No lending with provided id");
        }
        return ResponseEntity.status(200).body("Book return recorded");
    }

    @GetMapping("/admin/getAll")
    public List<LendingResponseDto> getAllLendings(){
        return lendingService.getLendedBooks();
    }


    @GetMapping("/getAll")
    public List<LendingResponseDto> getAllReservationsByMember(@RequestParam Integer memberId){
        if(memberService.getMemberById(memberId) == null){
            throw new MemberNotFoundException("No member with given id");
        }
        return lendingService.getUserLendedBooks(memberId);
    }

}
