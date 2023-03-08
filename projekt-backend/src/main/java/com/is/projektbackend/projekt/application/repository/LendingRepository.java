package com.is.projektbackend.projekt.application.repository;

import com.is.projektbackend.projekt.application.model.Lending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendingRepository extends JpaRepository<Lending, Integer> {

    List<Lending> findAllByMemberId(Integer memberId);
    List<Lending> findAllByBookId(Integer bookIs);
}
