package it.uniroma3.siw_booking.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw_booking.model.Hotel;
import it.uniroma3.siw_booking.model.Stanza;

public interface StanzaRepository extends CrudRepository<Stanza, Long>{
        public boolean existsByNomeAndHotel(String nome, Hotel hotel);
}
