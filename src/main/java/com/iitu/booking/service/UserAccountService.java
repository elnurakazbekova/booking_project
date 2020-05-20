package com.iitu.booking.service;

import com.iitu.booking.model.UserAccount;
import com.iitu.booking.model.UserRole;
import com.iitu.booking.repository.UserAccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserAccountService {
    private final UserAccountRepository userRepository;

    public UserAccountService(UserAccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserAccount addUser(UserAccount user){
        user.setRoles(Collections.singleton(UserRole.CUSTOMER));
        user.setActive(true);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
    public UserAccount findUser(UserAccount user){
        return userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail());
    }

    public List<UserAccount> getAll() {
        return userRepository.findAll();
    }

    public void delete(UserAccount user){
        this.userRepository.delete(user);
    }

    public UserAccount getUserByUsername(String username) {
        UserAccount user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        return null;
    }
}
