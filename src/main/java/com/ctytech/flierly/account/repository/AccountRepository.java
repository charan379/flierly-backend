package com.ctytech.flierly.account.repository;

import com.ctytech.flierly.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByRegisteredPhone(String registeredPhone);

    boolean existsByEmail(String email);
}
