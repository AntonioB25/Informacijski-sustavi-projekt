package com.is.projektbackend.projekt.application.repository;

import com.is.projektbackend.projekt.application.model.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipTypeRepository extends JpaRepository<MembershipType, Integer> {

    MembershipType getMembershipTypeById(Integer id);
    MembershipType findByMembershipTypeName(String membershipTypeName);

}
