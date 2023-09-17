package it.uniroma3.siw_booking.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw_booking.model.Stanza;
import it.uniroma3.siw_booking.service.StanzaService;

@Component
public class StanzaValidator implements Validator{

    @Autowired
    public StanzaService stanzaService;


    @Override
    public boolean supports(Class<?> clazz) {
       return Stanza.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		if (this.stanzaService.alreadyExists((Stanza)target))
			errors.reject("stanza.duplicato");
    }
    
}
