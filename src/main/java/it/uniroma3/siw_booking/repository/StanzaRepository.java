package it.uniroma3.siw_booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw_booking.model.Hotel;
import it.uniroma3.siw_booking.model.Stanza;

public interface StanzaRepository extends CrudRepository<Stanza, Long>{
        
        
        public boolean existsByNameAndHotel(String nome, Hotel hotel);

       
}
