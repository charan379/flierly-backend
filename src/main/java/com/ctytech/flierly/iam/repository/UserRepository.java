package com.ctytech.flierly.iam.repository;

import com.ctytech.flierly.iam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

