package com.voyage.agence.Controller;

import com.voyage.agence.Entity.Voyage;
import com.voyage.agence.Repository.TransportRepository;
import com.voyage.agence.Repository.VoyageRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/voyages")
public class VoyageController {

    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired
    private TransportRepository transportRepository;

    private String message = "";

    @GetMapping
    public String listVoyages(Model model) {
        List<Voyage> voyages = voyageRepository.findAll();
        model.addAttribute("voyages", voyages);
        model.addAttribute("message", message);
        message = "";
        return "Voyage/list";
    }

    @GetMapping("/add")
    public String showAddVoyageForm(Model model) {
        model.addAttribute("voyage", new Voyage());
        model.addAttribute("transports", transportRepository.findAll());
        return "Voyage/add";
    }

    @PostMapping("/add")
    public String addVoyage(@ModelAttribute @Valid Voyage voyage, Errors errors, Model model) {
        Optional<Voyage> voyageOptional = voyageRepository.findByTransportId(voyage.getTransport().getId());
        if (errors.hasErrors()) {
            model.addAttribute("transports", transportRepository.findAll());
            return "Voyage/add";
        }
        if (voyage.getDateFin().isBefore(voyage.getDateDebut())) {
            message = "La date de fin doit être  après la date de debut!";
            return "redirect:/voyages";
        }
        if (voyageOptional.isPresent()) {
            message = "Ce Transport est de déjà associé à un voyage , veillez sélectionner ou créer un nouveau transport pour ce voyage!";
            return "redirect:/voyages";
        }
        if (!voyage.getTransport().getDateDepart().isEqual(voyage.getDateDebut())
                || !voyage.getTransport().getDateArrivee().isEqual(voyage.getDateFin())) {
            message = "Les dates de départ et d'arrivée du voyage doivent correspondre à celles du transport.";
            return "redirect:/voyages";

        }
        voyageRepository.save(voyage);
        return "redirect:/voyages";
    }

    @GetMapping("/edit/{id}")
    public String showEditVoyageForm(@PathVariable Long id, Model model) {
        Voyage voyage = voyageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid voyage ID:" + id));
        model.addAttribute("voyage", voyage);
        model.addAttribute("transports", transportRepository.findAll());
        return "Voyage/edit";
    }

    @PostMapping("/edit/{id}")
    public String editVoyage(@PathVariable Long id, @ModelAttribute @Valid Voyage voyage, Errors errors) {
        if (errors.hasErrors()) {
            return "Voyage/edit";
        }
        voyage.setId(id);
        if (voyage.getDateFin().isBefore(voyage.getDateDebut())) {
            message = "La date de fin doit être  après la date de debut!";
            return "redirect:/voyages";
        }
        voyageRepository.save(voyage);
        return "redirect:/voyages";
    }

    @GetMapping("/delete/{id}")
    public String deleteVoyage(@PathVariable Long id) {
        voyageRepository.deleteById(id);
        return "redirect:/voyages";
    }

    @GetMapping("/filter")
    public String filterVoyages(@RequestParam(required = false) String destination,
            @RequestParam(required = false) Double maxPrix, Model model) {
        List<Voyage> voyages = voyageRepository.findAll();
        if (destination != null && !destination.isEmpty()) {
            voyages = voyageRepository.findByDestinationContaining(destination);
        }
        if (maxPrix != null) {
            voyages = voyageRepository.findByPrix(maxPrix);
        }
        model.addAttribute("voyages", voyages);
        return "Voyage/list";
    }
}
