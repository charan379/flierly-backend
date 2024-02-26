package com.ctytech.flierly.account.repository;

import com.ctytech.flierly.account.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

    Boolean existsByName(String name);
}
