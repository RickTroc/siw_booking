package it.uniroma3.siw_booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.uniroma3.siw_booking.model.Stanza;
import it.uniroma3.siw_booking.repository.StanzaRepository;
import jakarta.transaction.Transactional;

@Service
public class StanzaService {
    
    @Autowired
    private StanzaRepository stanzaRepository;


    @Transactional
    public void save(Stanza stanza){
        this.stanzaRepository.save(stanza);
    }

    @Transactional
    public Stanza findById(Long id){
        return this.stanzaRepository.findById(id).get();
    }

    @Transactional
    public List<Stanza> findAllStanza(){
        return (List<Stanza>)this.stanzaRepository.findAll();
    }

    @Transactional
    public void deletestanza(Long id){
        this.stanzaRepository.deleteById(id);
    }

    public boolean alreadyExists(Stanza stanza){
        return this.stanzaRepository.existsByNomeAndHotel(stanza.getName(),stanza.getHotel());
    }
}


