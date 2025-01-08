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

@Controller
@RequestMapping("/voyages")
public class VoyageController {

    @Autowired
    private VoyageRepository voyageRepository;

    @Autowired
    private TransportRepository transportRepository;

    @GetMapping
    public String listVoyages(Model model) {
        List<Voyage> voyages = voyageRepository.findAll();
        model.addAttribute("voyages", voyages);
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
        if (errors.hasErrors()) {
            model.addAttribute("transports", transportRepository.findAll());
            return "Voyage/add";
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
