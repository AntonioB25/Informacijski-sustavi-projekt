package com.is.projektbackend.projekt.application.repository;

import com.is.projektbackend.projekt.application.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Optional<Reservation> findById(Integer id);

    List<Reservation> findAllByMemberId(Integer memberId);

    List<Reservation> findAllByBookId(Integer bookId);
}
