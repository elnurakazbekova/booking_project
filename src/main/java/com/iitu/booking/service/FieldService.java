package com.iitu.booking.service;

import com.iitu.booking.model.Field;
import com.iitu.booking.model.UserAccount;
import com.iitu.booking.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private UserAccountService userAccountService;

    public List<Field> getAll() {
        return fieldRepository.findAll();
    }

//    public List<Field> getAllByCode(String code) {
//        return fieldRepository.findByCode(code);
//    }

    public Field addField(Field field, Authentication authentication){
        UserAccount userAccount = userAccountService.getUserByUsername(authentication.getName());
        field.setOwnerId(userAccount);
        return fieldRepository.save(field);
    }

    public void delete(Field field){
        fieldRepository.delete(field);
    }

}
