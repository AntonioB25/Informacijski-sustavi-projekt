package com.is.projektbackend.projekt.application.service.impl;
import com.is.projektbackend.projekt.application.dto.BookDto;
import com.is.projektbackend.projekt.application.dto.ReservationDetailsDto;
import com.is.projektbackend.projekt.application.dto.ReservationDto;
import com.is.projektbackend.projekt.application.model.Book;
import com.is.projektbackend.projekt.application.model.Lending;
import com.is.projektbackend.projekt.application.model.Member;
import com.is.projektbackend.projekt.application.model.Reservation;
import com.is.projektbackend.projekt.application.repository.BookRepository;
import com.is.projektbackend.projekt.application.repository.MemberRepository;
import com.is.projektbackend.projekt.application.repository.ReservationRepository;
import com.is.projektbackend.projekt.application.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationRepository reservationRepository;
    private BookRepository bookRepository;
    private MemberRepository memberRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, BookRepository bookRepository,
                                  MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }
    @Override
    public ReservationDetailsDto getReservationById(Integer id) {
        Reservation reservation = reservationRepository.getById(id);
        return convertToReservationDetailsDto(reservation);
    }
    @Override
    public Reservation makeReservation(ReservationDto reservationDto) {
        Book book = bookRepository.getById(reservationDto.getBookId());
        Member member = memberRepository.getById(reservationDto.getMemberId());
        LocalDate reservationDate = LocalDate.now();
        Reservation newReservation = new Reservation();
        newReservation.setBook(book);
        newReservation.setMember(member);
        newReservation.setReservationDate(reservationDate);
        return reservationRepository.save(newReservation);
    }
    @Override
    public Boolean removeReservation(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            return false;
        }
        reservationRepository.delete(reservation);
        return true;
    }
    @Override
    public List<ReservationDetailsDto> getAllReservations() {
        return reservationRepository.findAll().stream().map(this::convertToReservationDetailsDto).toList();
    }
    @Override
    public List<ReservationDetailsDto> getAllMemberReservations(Integer memberId) {
        return reservationRepository.findAllByMemberId(memberId).stream().map(this::convertToReservationDetailsDto).toList();
    }
    private ReservationDetailsDto convertToReservationDetailsDto(Reservation reservation){
        ReservationDetailsDto reservationDetails = new ReservationDetailsDto();
        reservationDetails.setId(reservation.getId());
        reservationDetails.setBookName(reservation.getBook().getBookName());
        reservationDetails.setBookId(reservation.getBook().getId());
        reservationDetails.setMemberId(reservation.getMember().getId());
        reservationDetails.setMemberName(reservation.getMember().getPerson().getNameLastname());
        reservationDetails.setMemberEmail(reservation.getMember().getPerson().getEmail());
        return reservationDetails;
    }
    private BookDto convertBookToDto(Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        bookDto.setGenreName(book.getGenre().getGenreName());
        bookDto.setSectionName(book.getSection().getSectionName());
//mozda dodat za stanje
        return bookDto;
    }
}