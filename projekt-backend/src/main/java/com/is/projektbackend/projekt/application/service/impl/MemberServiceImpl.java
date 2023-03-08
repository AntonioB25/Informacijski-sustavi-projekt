package com.is.projektbackend.projekt.application.service.impl;

import com.is.projektbackend.projekt.application.dto.BookDto;
import com.is.projektbackend.projekt.application.dto.CreateMemberDto;
import com.is.projektbackend.projekt.application.dto.MemberDetailsDto;
import com.is.projektbackend.projekt.application.exceptions.EmailAlreadyInUseException;
import com.is.projektbackend.projekt.application.model.Book;
import com.is.projektbackend.projekt.application.model.Member;
import com.is.projektbackend.projekt.application.model.Membership;
import com.is.projektbackend.projekt.application.model.MembershipType;
import com.is.projektbackend.projekt.application.model.Person;
import com.is.projektbackend.projekt.application.repository.BookRepository;
import com.is.projektbackend.projekt.application.repository.MemberRepository;
import com.is.projektbackend.projekt.application.repository.MembershipRepository;
import com.is.projektbackend.projekt.application.repository.MembershipTypeRepository;
import com.is.projektbackend.projekt.application.repository.PersonRepository;
import com.is.projektbackend.projekt.application.repository.UserAccountRepository;
import com.is.projektbackend.projekt.application.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private static final int                      MAX_BORROWED_BOOKS = 3;
    private              MemberRepository         memberRepository;
    private              PersonRepository         personRepository;
    private              MembershipRepository     membershipRepository;
    private              MembershipTypeRepository membershipTypeRepository;
    private              BookRepository           bookRepository;
    private              UserAccountRepository    userAccountRepository;

    @Autowired
    private ModelMapper     modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, PersonRepository personRepository,
        MembershipRepository membershipRepository, MembershipTypeRepository membershipTypeRepository,
        BookRepository bookRepository, UserAccountRepository userAccountRepository) {
        this.memberRepository = memberRepository;
        this.personRepository = personRepository;
        this.membershipRepository = membershipRepository;
        this.membershipTypeRepository = membershipTypeRepository;
        this.bookRepository = bookRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public Member addNewMember(CreateMemberDto createMemberDto) throws EmailAlreadyInUseException {

        if (personRepository.existsByEmail(createMemberDto.getEmail())) {
            throw new EmailAlreadyInUseException("Email already in use!");
        }

        Person person = new Person();
        person.setEmail(createMemberDto.getEmail());
        person.setNameLastname(createMemberDto.getNameAndLastName());
        personRepository.save(person);

        MembershipType membershipType =
            membershipTypeRepository.getMembershipTypeById(createMemberDto.getMembershipTypeId());

        if (membershipType == null) {
            throw new IllegalArgumentException("Membership type not found");
        }

        System.out.println(membershipType.getId());
        Membership membership = new Membership();
        membership.setMembershipDate(LocalDate.now());
        membership.setMembershipType(membershipType);
        membershipRepository.save(membership);

        Member newMember = new Member();
        newMember.setActivity(1);           // set member as active
        newMember.setBookNumber(0);         // set number of borrowed books to 0
        newMember.setMembership(membership);
        newMember.setPerson(person);

        return memberRepository.save(newMember);
    }

    @Override
    public Boolean removeMember(Integer memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return false;
        }

        memberRepository.delete(member);
        return true;
    }

    @Override
    public void setMemberActivity(Integer memberId, Integer activity) {

    }

    @Override
    public Boolean isMemberActive(Integer memberId) {
        Member member = memberRepository.getById(memberId);
        return member.getActivity() == 1;
    }

    @Override
    public Boolean hasReachedMaximumBorrowedBooks(Integer memberId) {
        Member member = memberRepository.getById(memberId);
        return member.getBookNumber() >= MAX_BORROWED_BOOKS;
    }

    @Override
    public Member getMemberById(Integer memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public List<MemberDetailsDto> getAllMembersDetails() {
        List<Member> members = memberRepository.findAll();
        List<MemberDetailsDto> memberDetailsDtoList = new ArrayList<>();

        for (Member member : members) {
            MemberDetailsDto memberDto = new MemberDetailsDto();
            memberDto.setMemberId(member.getId());
            memberDto.setNameLastName(member.getPerson().getNameLastname());
            memberDto.setEmail(member.getPerson().getEmail());
            memberDto.setNumberOfBorrowedBooks(member.getBookNumber());
            memberDto.setMembershipType(member.getMembership().getMembershipType().getMembershipTypeName());
            memberDto.setActivity(member.getActivity());

            List<Book> borrowedBooks = bookRepository.findAllByMemberId(member.getId());
            List<BookDto> booksDto = borrowedBooks.stream().map(this::convertBookToDto).toList();
            memberDto.setBorrowedBooks(booksDto);
            memberDetailsDtoList.add(memberDto);
        }

        return memberDetailsDtoList;
    }

    private BookDto convertBookToDto(Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        bookDto.setGenreName(book.getGenre().getGenreName());
        bookDto.setSectionName(book.getSection().getSectionName());
        //mozda dodat za stanje
        return bookDto;
    }

}
