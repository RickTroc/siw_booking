package it.uniroma3.siw_booking.controller.validator;

import org.apache.catalina.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
       return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validate'");
    }
    
}
