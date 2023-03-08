package com.is.projektbackend.projekt.application.repository;

import com.is.projektbackend.projekt.application.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findAll();
    Optional<Member> findById(Integer id);
    Optional<Member> findMemberByPersonNameLastnameContaining(String nameLastName);
}
