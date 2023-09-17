package it.uniroma3.siw_booking.controller;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import it.uniroma3.siw_booking.controller.validator.HotelValidator;
import it.uniroma3.siw_booking.model.Hotel;
import it.uniroma3.siw_booking.model.Stanza;
import it.uniroma3.siw_booking.service.HotelService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class HotelController {
    
    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelValidator hotelValidator;

    @GetMapping(value="/hotel/{id}")
    public String getHotel(@PathVariable("id" ) Long Id, Model model) {
        Hotel hotel = this.hotelService.findById(Id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("stanze", hotel.getStanze());
        return "hotel.html";
    }

    @GetMapping(value = "/hotels")
    public String getAllHotels(Model model){
        model.addAttribute("hotels", this.hotelService.allHotels());
        return "hotels.html";
    }

    @GetMapping(value = "/admin/formNewHotel")
    public String formNewHotel(Model model){
        model.addAttribute("hotel", new Hotel());
        return "admin/formNewHotel.html";
    }

    @PostMapping(value="/admin/hotel")
    public String newHotel(@Valid @ModelAttribute("hotel") Hotel hotel, Model model, BindingResult bindingResult) throws IOException {
       
        this.hotelValidator.validate(hotel, bindingResult);
        if(!bindingResult.hasErrors()){
            this.hotelService.save(hotel);
            model.addAttribute("hotel", hotel);
            return "hotel.html";
        }
        else{
            model.addAttribute("messaggioErrore", "Questo hotel esiste gia'");
            return "admin/formNewHotel.html";
        } 
       
    }

    @GetMapping(value = "/admin/manageHotel")
    public String manageHotel(Model model){
        model.addAttribute("hotels", this.hotelService.allHotels());
        return "/admin/manageHotel.html";
    }
    
    @GetMapping(value = "/admin/deleteHotel")
    public String deleteHotel(Model model){
        model.addAttribute("hotels", this.hotelService.allHotels());
        return "/admin/deleteHotel.html";
    }
    

    @GetMapping(value = "/admin/deleteHotel/{id}")
    public String deleteHotel(@PathVariable("id") Long id, Model model){
            this.hotelService.deleteHotel(id);
            model.addAttribute("hotels", this.hotelService.allHotels());
            return "/admin/deleteHotel.html";
    }

    @GetMapping(value = "/admin/updateHotel/{id}")
    public String updateHotel(@PathVariable("id") Long id, Model model){
        model.addAttribute("hotel", this.hotelService.findById(id));
        return "admin/formUpdateHotel.html";
    }

    @PostMapping("/admin/updateHotel")
    public String updateHotel(@PathVariable("id") Long id, @ModelAttribute("hotel") Hotel hotel, @RequestParam(required = false, name = "stanze") List<Stanza> stanze, Model model){
        Hotel old = this.hotelService.findById(id);

        if(hotel.getNome() != null) old.setNome(hotel.getNome());
        if(hotel.getIndirizzo()!= null) old.setIndirizzo(hotel.getIndirizzo());
        if(!hotel.getStanze().isEmpty()) old.setStanze(stanze);

        this.hotelService.save(old);
        model.addAttribute("oldHotel", old);
        model.addAttribute("stanze", old.getStanze());

        return "hotel.html";
    }

}
