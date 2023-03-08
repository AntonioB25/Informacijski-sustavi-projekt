package com.is.projektbackend.projekt.application.controller;

import com.is.projektbackend.projekt.application.dto.LoginDto;
import com.is.projektbackend.projekt.application.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger                LOG = LoggerFactory.getLogger(AuthController.class);
    private              AuthenticationManager authenticationManager;
    // private              UserAccountService    userAccountService;
    //  private              PasswordEncoder       passwordEncoder;
    private              PersonRepository      personRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, PersonRepository personRepository) {
        this.authenticationManager = authenticationManager;
        this.personRepository = personRepository;
    }

    //    public AuthController(AuthenticationManager authenticationManager, UserAccountService userAccountService,
    //        PasswordEncoder passwordEncoder, PersonRepository personRepository) {
    //        this.authenticationManager = authenticationManager;
    //        this.userAccountService = userAccountService;
    //        this.passwordEncoder = passwordEncoder;
    //        this.personRepository = personRepository;
    //    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        System.out.println("EJ UNUTRA SASM;");
        SCryptPasswordEncoder encoder = new SCryptPasswordEncoder(16384, 8, 1, 16,8);

        String password = encoder.encode(loginDto.getPassword());

        System.out.println("Pass: " + password + "  Lendgth: " + password.length());
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//            loginDto.getUsername(), loginDto.getPassword()));

//        System.out.println("***********************");
//        System.out.println(
//            loginDto.getPassword() + " : " + loginDto.getUsername() + " : " + authentication.isAuthenticated());
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    //    @PostMapping("/register")
    //    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
    //
    //        // add check for username exists in a DB
    //        if (userAccountService.usernameExists(registerDto.getAccountName())) {
    //            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
    //        }
    //
    //        // create user object
    //        UserAccount userAccount = new UserAccount();
    //        Person person = new Person();
    //        person.setNameLastname(registerDto.getNameAndLastName());
    //        person.setEmail(registerDto.getEmail());
    //
    //        personRepository.save(person);
    //
    //        userAccount.setAccountName(registerDto.getAccountName());
    //        userAccount.setPerson(person);
    //        //userAccount.setPassword(passwordEncoder.encode(registerDto.getPassword()));
    //        userAccount.setPassword(registerDto.getPassword()); //TODO: change to previous line, for now leave it unencrypted
    //        System.out.println(registerDto.getPassword());
    //        AccountType role = userAccountService.getAccountType("zaposlenicki");
    //        userAccount.setAccountType(role);
    //
    //        userAccountService.addUserAccount(userAccount);
    //
    //        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    //
    //    }

}
