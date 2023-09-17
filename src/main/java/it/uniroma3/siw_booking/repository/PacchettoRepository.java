package it.uniroma3.siw_booking.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw_booking.model.Pacchetto;
import it.uniroma3.siw_booking.model.Stanza;

public interface PacchettoRepository extends CrudRepository<Pacchetto, Long>{
    
    public boolean existsByNomeAndStanza(String nome, Stanza stanza);
}
