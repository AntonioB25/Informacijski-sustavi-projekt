package com.is.projektbackend.projekt.application.service;

import com.is.projektbackend.projekt.application.dto.ReservationDetailsDto;
import com.is.projektbackend.projekt.application.dto.ReservationDto;
import com.is.projektbackend.projekt.application.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    ReservationDetailsDto getReservationById(Integer id); //  DTO

    Reservation makeReservation(ReservationDto reservationDto);

    Boolean removeReservation(Integer reservationId);

    List<ReservationDetailsDto> getAllReservations();

    List<ReservationDetailsDto> getAllMemberReservations(Integer memberId);
}
