package com.is.projektbackend.projekt.application.repository;

import com.is.projektbackend.projekt.application.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    Optional<UserAccount> findById(Integer id);

    Optional<UserAccount> findByAccountName(String username);

    Boolean existsByAccountName(String username);

}
