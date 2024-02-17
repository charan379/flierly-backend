package com.ctytech.flierly.account.repository;

import com.ctytech.flierly.account.entity.AccountSubtype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSubtypeRepository extends JpaRepository<AccountSubtype, Long> {

    Boolean existsByName(String name);
}
