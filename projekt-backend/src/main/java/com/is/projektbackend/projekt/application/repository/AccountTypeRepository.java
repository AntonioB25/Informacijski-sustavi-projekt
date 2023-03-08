package com.is.projektbackend.projekt.application.repository;

import com.is.projektbackend.projekt.application.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {

    Optional<AccountType> findByAccountTypeName(String accountTypeName);

}
