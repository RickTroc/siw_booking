package it.uniroma3.siw_booking.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw_booking.model.Pacchetto;

public interface PacchettoRepository extends CrudRepository<Pacchetto, Long>{
    
    public boolean existsByNomeAndDescrizione(String nome, String descrizione);
}
