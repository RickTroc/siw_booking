package it.uniroma3.siw_booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw_booking.model.Pacchetto;
import it.uniroma3.siw_booking.repository.PacchettoRepository;
import jakarta.transaction.Transactional;

@Service
public class PacchettoService {
    
    @Autowired
    private PacchettoRepository pacchettoRepository;

    @Transactional
    public void save(Pacchetto pacchetto){
        this.pacchettoRepository.save(pacchetto);
    }

    @Transactional
    public Pacchetto findById(Long id){
        return this.pacchettoRepository.findById(id).get();
    }

    @Transactional
    public List<Pacchetto> findAllPacchetto(){
        return (List<Pacchetto>)this.pacchettoRepository.findAll();
    }

    @Transactional
    public void deletePacchetto(Long id){
        this.pacchettoRepository.deleteById(id);
    }

    public boolean alredyExist(Pacchetto pacchetto){
        return this.pacchettoRepository.existsByNomeAndDescrizione(pacchetto.getNome(), pacchetto.getDescrizione());
    }

}
