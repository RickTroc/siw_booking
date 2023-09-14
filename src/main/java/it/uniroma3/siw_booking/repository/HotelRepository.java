package it.uniroma3.siw_booking.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw_booking.model.Hotel;


public interface HotelRepository extends CrudRepository<Hotel, Long>{

    public boolean existsByNomeAndIndirizzo(String nome, String indirizzo);
    
}
