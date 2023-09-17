package it.uniroma3.siw_booking.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw_booking.model.Pacchetto;
import it.uniroma3.siw_booking.service.PacchettoService;
@Component
public class PacchettoValidator implements Validator{

    @Autowired
    private PacchettoService pacchettoService;


    

    @Override
    public boolean supports(Class<?> clazz) {
        return Pacchetto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
      
		if (this.pacchettoService.alredyExist((Pacchetto)target))
			errors.reject("pacchetto.duplicato");
    }
    
}
