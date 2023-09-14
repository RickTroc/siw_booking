package it.uniroma3.siw_booking.controller;

import java.io.IOError;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import it.uniroma3.siw_booking.controller.validator.HotelValidator;
import it.uniroma3.siw_booking.model.Hotel;
import it.uniroma3.siw_booking.service.HotelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String getHotel(@RequestParam("id") Long Id, Model model) {
        Hotel hotel = this.hotelService.findById(Id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("stanze", hotel.getStanze());
        return "hotel.html";
    }

    @GetMapping(value = "/hotels")
    public String getAllHotels(Model model){
        model.addAttribute("hotels", this.hotelService.allHotels());
        return "/hotels.html";
    }

    @GetMapping(value = "/admin/formNewHotel")
    public String formNewHotel(Model model){
        model.addAttribute("hotel", new Hotel());
        return "admin/formNewHotel";
    }

    @PostMapping(value="/admin/hotel")
    public String newHotel(@Validated @ModelAttribute("hotel") Hotel hotel, Model model, BindingResult bindingResult) throws IOException {
       
        this.hotelValidator.validate(hotelService, null);
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
    
    



}
