package com.iitu.booking.repository;

import com.iitu.booking.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long> {

    UserAccount findByUsernameAndEmail(String username, String email);

    UserAccount findByUsername(String username);
}
