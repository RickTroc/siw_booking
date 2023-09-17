package it.uniroma3.siw_booking.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw_booking.model.Hotel;
import it.uniroma3.siw_booking.repository.HotelRepository;

import java.lang.Object;

@Component
public class HotelValidator implements Validator{

    
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        
        return Hotel.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
       
            Hotel hotel = (Hotel) o;
            
            
            if (hotel.getNome()!= null &&
                hotel.getIndirizzo() != null &&
                this.hotelRepository.existsByNomeAndIndirizzo(hotel.getNome(), hotel.getIndirizzo()))
                errors.reject("hotel.duplicato");
        
    }
    
}
