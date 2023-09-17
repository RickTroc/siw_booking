package it.uniroma3.siw_booking.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw_booking.controller.validator.StanzaValidator;
import it.uniroma3.siw_booking.model.Hotel;
import it.uniroma3.siw_booking.model.Stanza;
import it.uniroma3.siw_booking.service.HotelService;
import it.uniroma3.siw_booking.service.StanzaService;
import jakarta.validation.Valid;

@Controller
public class StanzaController {
    
   
    @Autowired
    private StanzaService stanzaService;

    @Autowired
    private StanzaValidator stanzaValidator;

    @Autowired
    private HotelService hotelService;

    @GetMapping(value = "/stanza/{id}")
    public String getStanza(@PathVariable("id") Long id, Model model) {
        Stanza stanza = this.stanzaService.findById(id);
        model.addAttribute("stanza", stanza);
        model.addAttribute("pacchetti", stanza.getPacchetti());
        return "stanza.html";
    }

    @GetMapping(value = "/stanze")
    public String getAllStanze(Long hotelId ,Model model) {
      

        model.addAttribute("hotels", this.hotelService.allHotels());
        model.addAttribute("stanze", this.stanzaService.findAllStanza()); //questo non serve...
       
        return "stanze.html";
    }



    @GetMapping(value = "/admin/formNewStanza")
    public String formNewStanza(Model model) {
        model.addAttribute("stanza", new Stanza());
        model.addAttribute("hotels", this.hotelService.allHotels());
        return "admin/formNewStanza";
    }

    @PostMapping(value = "/admin/stanza")
    public String newStanza(@Valid @ModelAttribute("stanza") Stanza stanza, Model model, BindingResult bindingResult) throws IOException{
        this.stanzaValidator.validate(stanza, bindingResult);
        
        if (!bindingResult.hasErrors()) {
            this.stanzaService.save(stanza);
            return "stanza.html";
        } else {
            model.addAttribute("messaggioErrore", "Questa stanza non è valida");
            return "admin/formNewStanza";
        }
    }

    @GetMapping(value = "/admin/manageStanze")
    public String manageStanze(Model model) {
        model.addAttribute("stanze", this.stanzaService.findAllStanza());
        return "admin/manageStanze.html";
    }


      @GetMapping(value = "/admin/deleteStanza")
    public String deleteStanza(Model model) {
        model.addAttribute("hotels", this.hotelService.allHotels());
       model.addAttribute("stanze", this.stanzaService.findAllStanza());
        return "admin/deleteStanza.html";
    }

    @GetMapping(value = "/admin/deleteStanza/{id}")
    public String deleteStanza(@PathVariable("id") Long id) {
        this.stanzaService.deletestanza(id);;
        return "admin/deleteStanza.html";
    }

    @GetMapping(value = "/admin/updateStanza/{id}")
    public String updateStanza(@PathVariable("id") Long id, Model model) {
        Stanza stanza = this.stanzaService.findById(id);
        model.addAttribute("stanza", stanza);
        return "admin/formUpdateStanza.html";
    }

    @PostMapping("/admin/updateStanza")
    public String updateStanza(@ModelAttribute("stanza") Stanza stanza, Model model) {
        Stanza oldStanza = this.stanzaService.findById(stanza.getId());

       
        //TODO: Da finire

        this.stanzaService.save(oldStanza);
        return "stanza.html";
    }


}
