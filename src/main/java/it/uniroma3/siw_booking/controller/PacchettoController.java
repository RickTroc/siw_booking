package it.uniroma3.siw_booking.controller;


import java.io.IOError;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw_booking.controller.validator.PacchettoValidator;
import it.uniroma3.siw_booking.model.Pacchetto;
import it.uniroma3.siw_booking.service.PacchettoService;
import it.uniroma3.siw_booking.service.StanzaService;
import jakarta.validation.Valid;

@Controller
public class PacchettoController {
 
     @Autowired
    private PacchettoService pacchettoService;

    @Autowired
    private PacchettoValidator pacchettoValidator;

    @Autowired
    private StanzaService stanzaService;

    @GetMapping(value = "/pacchetto/{id}")
    public String getPacchetto(@PathVariable("id") Long id, Model model) {
        Pacchetto pacchetto = this.pacchettoService.findById(id);
        model.addAttribute("pacchetto", pacchetto);
        return "pacchetto.html";
    }

    @GetMapping(value = "/pacchetti")
    public String getAllPacchetti(Model model) {
        model.addAttribute("pacchetti", this.pacchettoService.findAllPacchetto());
        return "pacchetti.html";
    }

    @GetMapping(value = "/admin/formNewPacchetto")
    public String formNewPacchetto(Model model) {
        model.addAttribute("pacchetto", new Pacchetto());
        model.addAttribute("stanze", this.stanzaService.findAllStanza());
        return "admin/formNewPacchetto";
    }

    @PostMapping(value = "/admin/pacchetto")
        public String newPacchetto(@Valid @ModelAttribute("pacchetto") Pacchetto pacchetto, Model model, BindingResult bindingResult) throws IOException{

        this.pacchettoValidator.validate(pacchetto, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.pacchettoService.save(pacchetto);
            model.addAttribute("pacchetto", pacchetto);
            return "pacchetto.html";
        } else {
            model.addAttribute("messaggioErrore", "Questo pacchetto non è valido");
            return "admin/formNewPacchetto";
        }
    }

    @GetMapping(value = "/admin/managePacchetti")
    public String managePacchetti(Model model) {
        model.addAttribute("pacchetti", this.pacchettoService.findAllPacchetto());
        return "admin/managePacchetti.html";
    }

    @GetMapping(value = "/admin/deletePacchetto")
    public String deletePacchetto(Model model) {
        model.addAttribute("stanze", this.stanzaService.findAllStanza());
        model.addAttribute("pacchetti", this.pacchettoService.findAllPacchetto());
        return "admin/deletePacchetto.html";
    }

    @GetMapping(value = "/admin/deletePacchetto/{id}")
    public String deletePacchetto(@PathVariable("id") Long id) {
        this.pacchettoService.deletePacchetto(id);
        return "admin/deletePacchetto.html";
    }

    @GetMapping(value = "/admin/updatePacchetto/{id}")
    public String updatePacchetto(@PathVariable("id") Long id, Model model) {
        Pacchetto pacchetto = this.pacchettoService.findById(id);
        model.addAttribute("pacchetto", pacchetto);
        return "admin/formUpdatePacchetto.html";
    }

    @PostMapping("/admin/updatePacchetto")
    public String updatePacchetto(@ModelAttribute("pacchetto") Pacchetto pacchetto, Model model) {
        Pacchetto oldPacchetto = this.pacchettoService.findById(pacchetto.getId());

        oldPacchetto.setNome(pacchetto.getNome());
        oldPacchetto.setDescrizione(pacchetto.getDescrizione());
        oldPacchetto.setCosto(pacchetto.getCosto());
        oldPacchetto.setStanza(pacchetto.getStanza());
       

        this.pacchettoService.save(oldPacchetto);
        return "pacchetto.html";
    }


}
