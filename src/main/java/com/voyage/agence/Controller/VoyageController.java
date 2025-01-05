package com.voyage.agence.Controller;

import com.voyage.agence.Entity.Voyage;
import com.voyage.agence.Repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/voyages")
public class VoyageController {

    @Autowired
    private VoyageRepository voyageRepository;

    @GetMapping
    public String listVoyages(Model model) {
        List<Voyage> voyages = voyageRepository.findAll();
        model.addAttribute("voyages", voyages);
        return "voyage/list";
    }

    @GetMapping("/add")
    public String showAddVoyageForm(Model model) {
        model.addAttribute("voyage", new Voyage());
        return "voyage/add";
    }

    @PostMapping("/add")
    public String addVoyage(@ModelAttribute Voyage voyage) {
        voyageRepository.save(voyage);
        return "redirect:/voyages";
    }

    @GetMapping("/edit/{id}")
    public String showEditVoyageForm(@PathVariable Long id, Model model) {
        Voyage voyage = voyageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid voyage ID:" + id));
        model.addAttribute("voyage", voyage);
        return "voyage/edit";
    }

    @PostMapping("/edit/{id}")
    public String editVoyage(@PathVariable Long id, @ModelAttribute Voyage voyage) {
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
            voyages = voyages.stream().filter(v -> v.getDestination().equalsIgnoreCase(destination)).toList();
        }
        if (maxPrix != null) {
            voyages = voyages.stream().filter(v -> v.getPrix() <= maxPrix).toList();
        }
        model.addAttribute("voyages", voyages);
        return "voyage/list";
    }
}
