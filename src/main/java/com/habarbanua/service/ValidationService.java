package com.habarbanua.service;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ValidationService {

    @Autowired
    private Validator validator;

    public void validate(Object o){
        var violations = validator.validate(o);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }

    public void isSameUser(Long o1, Long o2){
        if (!o1.equals(o2)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "mengedit milik orang lain tidak di izinkan");
        }
    }

}
