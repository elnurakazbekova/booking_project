package com.iitu.booking.service;

import com.iitu.booking.model.Field;
import com.iitu.booking.model.FieldType;
import com.iitu.booking.repository.FieldTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldTypeService {

    @Autowired
    private FieldTypeRepository fieldTypeRepository;

    public List<FieldType> getAllFieldTypes() {
        return fieldTypeRepository.findAll();
    }

    public FieldType addFieldType(FieldType fieldType){
        return fieldTypeRepository.save(fieldType);
    }

    public void delete(FieldType fieldType){
        fieldTypeRepository.delete(fieldType);
    }

}
