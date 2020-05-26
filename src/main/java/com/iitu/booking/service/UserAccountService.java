package com.iitu.booking.service;

import com.iitu.booking.model.UserAccount;
import com.iitu.booking.model.UserRole;
import com.iitu.booking.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccount addUser(UserAccount user){
        if (user.getRoleId() == Long.valueOf(1))
            user.setRole(Collections.singleton(UserRole.ADMIN));
        else
            user.setRole(Collections.singleton(UserRole.CUSTOMER));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userAccountRepository.save(user);
    }
    public UserAccount findUser(UserAccount user){
        return userAccountRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail());
    }

    public List<UserAccount> getAll() {
        return userAccountRepository.findAll();
    }

    public void delete(UserAccount user){
        this.userAccountRepository.delete(user);
    }

    public UserAccount getUserByUsername(String username) {
        UserAccount user = userAccountRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        return null;
    }
}
