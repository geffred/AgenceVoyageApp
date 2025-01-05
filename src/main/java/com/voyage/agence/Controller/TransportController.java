package com.voyage.agence.Controller;

import com.voyage.agence.Entity.Transport;
import com.voyage.agence.Repository.TransportRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transports")
public class TransportController {

    @Autowired
    private TransportRepository transportRepository;

    @GetMapping
    public String listTransports(Model model) {
        List<Transport> transports = transportRepository.findAll();
        model.addAttribute("transports", transports);
        return "Transport/list";
    }

    @GetMapping("/add")
    public String showAddTransportForm(Model model) {
        model.addAttribute("transport", new Transport());
        return "Transport/add";
    }

    @PostMapping("/add")
    public String addTransport(@ModelAttribute @Valid Transport transport, Errors errors) {
        if (errors.hasErrors()) {
            return "Transport/add";
        }
        transportRepository.save(transport);
        return "redirect:/transports";
    }

    @GetMapping("/edit/{id}")
    public String showEditTransportForm(@PathVariable Long id, Model model) {
        Transport transport = transportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid transport ID:" + id));
        model.addAttribute("transport", transport);
        return "Transport/edit";
    }

    @PostMapping("/edit/{id}")
    public String editTransport(@PathVariable Long id, @ModelAttribute @Valid Transport transport, Errors errors) {
        if (errors.hasErrors()) {
            return "Transport/edit";
        }
        transport.setId(id);
        transportRepository.save(transport);
        return "redirect:/transports";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransport(@PathVariable Long id) {
        transportRepository.deleteById(id);
        return "redirect:/transports";
    }
}
