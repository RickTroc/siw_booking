package it.uniroma3.siw_booking.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw_booking.model.Hotel;
import it.uniroma3.siw_booking.service.HotelService;

public class HotelValidator implements Validator{

    @Autowired
    private HotelService hotelService;

    @Override
    public boolean supports(Class<?> clazz) {
        
        return Hotel.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		if (this.hotelService.existByName((Hotel)target))
			errors.reject("hotel.duplicato");
    }
    
}
