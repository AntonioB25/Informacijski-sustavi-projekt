//package com.is.projektbackend.projekt.application.service.impl;
//
//import com.is.projektbackend.projekt.application.model.AccountType;
//import com.is.projektbackend.projekt.application.model.UserAccount;
//import com.is.projektbackend.projekt.application.repository.AccountTypeRepository;
//import com.is.projektbackend.projekt.application.repository.UserAccountRepository;
//import com.is.projektbackend.projekt.application.service.UserAccountService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class UserAccountServiceImpl implements UserAccountService {
//
//    private UserAccountRepository userAccountRepository;
//    private AccountTypeRepository accountTypeRepository;
//
//
//    @Autowired
//    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, AccountTypeRepository accountTypeRepository) {
//        this.userAccountRepository = userAccountRepository;
//        this.accountTypeRepository = accountTypeRepository;
//    }
//
//    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<AccountType> roles){
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAccountTypeName())).collect(Collectors.toList());
//    }
//
//    @Override
//    public Boolean usernameExists(String username) {
//        return userAccountRepository.existsByAccountName(username);
//    }
//
//    @Override
//    public UserAccount addUserAccount(UserAccount userAccount) {
//        return userAccountRepository.save(userAccount);
//    }
//
//    @Override
//    public AccountType getAccountType(String accountType) {
//        return accountTypeRepository.findByAccountTypeName(accountType).get();
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<AccountType> roles = accountTypeRepository.findAll();
//
//        return userAccountRepository.findByAccountName(username)
//            .orElseThrow(() ->
//                new UsernameNotFoundException("User not found with username or email:" + username));
//    }
//
//}
