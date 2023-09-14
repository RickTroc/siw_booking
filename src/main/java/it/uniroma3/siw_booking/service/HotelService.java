package it.uniroma3.siw_booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw_booking.model.Hotel;
import it.uniroma3.siw_booking.repository.HotelRepository;
import jakarta.transaction.Transactional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    @Transactional
    public void save(Hotel hotel){
        this.hotelRepository.save(hotel);
    }

    public Hotel findById(Long id){
        return this.hotelRepository.findById(id).get();
    }

    @Transactional
    public List<Hotel> allHotels(){
        return (List<Hotel>) this.hotelRepository.findAll();
    }


    @Transactional
    public void deleteHotel(Long id){
        this.hotelRepository.deleteById(id);
    }




    public boolean existByName(Hotel hotel) {
        return this.hotelRepository.existsByNomeAndIndirizzo(hotel.getNome(), hotel.getIndirizzo());
    }
    
}
