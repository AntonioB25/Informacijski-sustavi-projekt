package com.is.projektbackend.projekt.application.controller;

import com.is.projektbackend.projekt.application.dto.CreateMemberDto;
import com.is.projektbackend.projekt.application.dto.MemberDetailsDto;
import com.is.projektbackend.projekt.application.exceptions.EmailAlreadyInUseException;
import com.is.projektbackend.projekt.application.exceptions.MemberNotFoundException;
import com.is.projektbackend.projekt.application.model.Member;
import com.is.projektbackend.projekt.application.service.MemberService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/admin/addNewMember")
    public ResponseEntity<String> addNewMember(@RequestBody CreateMemberDto createMemberDto) {
        try {
            memberService.addNewMember(createMemberDto);
        } catch (EmailAlreadyInUseException e) {
            return ResponseEntity.badRequest().body("Member already exists");
        }

        System.out.println(createMemberDto.toString());
        return ResponseEntity.ok().body("Member created");
    }

    @RequestMapping(value = "/{memberId}", method = RequestMethod.GET)
    public Member getMember(@PathVariable @NotNull Integer memberId) {
        Member member = memberService.getMemberById(memberId);
        if (member == null) {
            throw new MemberNotFoundException("No member with provided id");
        }
        return member;
    }

    @DeleteMapping("/admin/removeMember")
    public ResponseEntity<String> removeMember(@RequestParam Integer memberId) {
        if (!memberService.removeMember(memberId)) {
            throw new MemberNotFoundException("No member with provided id");
        }
        return ResponseEntity.status(200).body("Member removed");

    }
    //reigster member

    @GetMapping("/admin/getAll")
    public List<MemberDetailsDto> getAllMembersDetails() {
        return memberService.getAllMembersDetails();
    }

}
