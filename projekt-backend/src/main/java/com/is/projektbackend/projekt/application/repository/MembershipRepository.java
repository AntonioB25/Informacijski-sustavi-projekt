package com.is.projektbackend.projekt.application.repository;

import com.is.projektbackend.projekt.application.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {
    Membership findMembershipById(Integer id);
}
