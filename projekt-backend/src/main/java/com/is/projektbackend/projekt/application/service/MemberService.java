package com.is.projektbackend.projekt.application.service;

import com.is.projektbackend.projekt.application.dto.MemberDetailsDto;
import com.is.projektbackend.projekt.application.dto.CreateMemberDto;
import com.is.projektbackend.projekt.application.exceptions.EmailAlreadyInUseException;
import com.is.projektbackend.projekt.application.model.Member;

import java.util.List;

public interface MemberService {

    Member addNewMember(CreateMemberDto createMemberDto) throws EmailAlreadyInUseException;

    List<MemberDetailsDto> getAllMembersDetails();
    Member getMemberById(Integer memberId);
    Boolean removeMember(Integer memberId);
    void setMemberActivity(Integer memberId, Integer activity);

    Boolean isMemberActive(Integer memberId);

    Boolean hasReachedMaximumBorrowedBooks(Integer memberId);

}
