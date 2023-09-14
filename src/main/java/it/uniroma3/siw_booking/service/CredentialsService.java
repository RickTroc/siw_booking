package it.uniroma3.siw_booking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw_booking.model.Credentials;
import it.uniroma3.siw_booking.model.User;
import it.uniroma3.siw_booking.repository.CredentialsRepository;

@Service
public class CredentialsService {
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired CredentialsRepository credentialsRepository;

    @Transactional
    public Credentials getCredentials(Long id) {
        Optional<Credentials> result = this.credentialsRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public Credentials getCredentials(String username){
        Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
        return result.orElse(null);
    }

    @Transactional
    public Credentials saveCredentials(Credentials credentials){
        credentials.setRole(Credentials.DEFAULT_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }

    public Credentials getLoggedCredentials() {
    	UserDetails loggedUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  this.credentialsRepository.findByUsername(loggedUserDetails.getUsername()).get();
    }
    
    public User getLoggedUser() {
        return this.getLoggedCredentials().getUser();
    }

}
