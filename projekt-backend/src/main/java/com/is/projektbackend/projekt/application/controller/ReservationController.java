package com.is.projektbackend.projekt.application.controller;

import com.is.projektbackend.projekt.application.dto.ReservationDetailsDto;
import com.is.projektbackend.projekt.application.dto.ReservationDto;
import com.is.projektbackend.projekt.application.exceptions.BookNotFreeException;
import com.is.projektbackend.projekt.application.exceptions.LendingNotFoundException;
import com.is.projektbackend.projekt.application.exceptions.MemberNotActiveException;
import com.is.projektbackend.projekt.application.exceptions.MemberNotFoundException;
import com.is.projektbackend.projekt.application.model.Reservation;
import com.is.projektbackend.projekt.application.service.BookService;
import com.is.projektbackend.projekt.application.service.MemberService;
import com.is.projektbackend.projekt.application.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationController {

    private final ReservationService reservationService;
    private       MemberService      memberService;
    private       BookService        bookService;

    @Autowired
    public ReservationController(ReservationService reservationService, MemberService memberService,
        BookService bookService) {
        this.reservationService = reservationService;
        this.memberService = memberService;
        this.bookService = bookService;
    }




    @GetMapping("/admin/getAll")
    public List<ReservationDetailsDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    /**
     * Get all reservations of a member
     *
     * @param memberId
     * @return
     */
    @GetMapping("/getAll")
    public List<ReservationDetailsDto> getAllReservationsByMember(@RequestParam Integer memberId) {
        if (memberService.getMemberById(memberId) == null) {
            throw new MemberNotFoundException("No member with given id");
        }
        return reservationService.getAllMemberReservations(memberId);
    }

    //TODO: vidi ima li kakva ograničenja na broj rezerviranih knjiga
    @PostMapping("/makeReservation")
    public Reservation makeReservation(@Valid @RequestBody ReservationDto reservationDto) {
        if (!memberService.isMemberActive(reservationDto.getMemberId())) {
            throw new MemberNotActiveException("Member not active, cannot reserve book");
        }

        if (!bookService.isBookFree(reservationDto.getBookId())){
            throw new BookNotFreeException("Book already reserved or lended");
        }

        //        if (memberService.hasReachedMaximumBorrowedBooks(lendingDto.getMemberId())) {
        //            throw new BorrowedLimitException("Member has reached maximum number borrowed books");
        //        }

        {
            return reservationService.makeReservation(reservationDto);
        }
    }

    @DeleteMapping("/removeReservation")
    public ResponseEntity<String> removeReservation(@RequestParam Integer reservationId) {
        if (!reservationService.removeReservation(reservationId)) {
            throw new LendingNotFoundException("No reservation with provided id");
        }
        return ResponseEntity.status(200).body("Reservation remove recorded");
    }

    //TOOD: vidi treba li ovo uopće?
    //TODO: popravi ovo da je ko one gore i da baca excepriont
    @GetMapping("/getReservation")
    public ReservationDetailsDto getReservation(@RequestParam Integer reservationId) {
        return reservationService.getReservationById(reservationId);
    }

}
