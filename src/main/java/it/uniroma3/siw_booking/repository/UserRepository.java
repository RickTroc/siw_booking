package it.uniroma3.siw_booking.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw_booking.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
    
    
}
