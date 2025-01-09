package com.voyage.agence.Controller;

import com.voyage.agence.Entity.Client;
import com.voyage.agence.Repository.ClientRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    private String message = "";

    @GetMapping
    public String listClients(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        model.addAttribute("message", message);
        message = "";
        return "Client/list";
    }

    @GetMapping("/add")
    public String showAddClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "Client/add";
    }

    @PostMapping("/add")
    public String addClient(@ModelAttribute @Valid Client client, Errors errors) {
        if (errors.hasErrors()) {
            return "Client/add";
        }
        try {
            clientRepository.save(client);
        } catch (Exception e) {
            message = "l'email ou le numéro de téléhphone est déjà utilisé";
        }
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String showEditClientForm(@PathVariable Long id, Model model) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client ID:" + id));
        model.addAttribute("client", client);
        return "Client/edit";
    }

    @PostMapping("/edit/{id}")
    public String editClient(@PathVariable Long id, @ModelAttribute @Valid Client client, Errors errors) {
        if (errors.hasErrors()) {
            return "Client/edit";
        }
        client.setId(id);
        try {
            clientRepository.save(client);
        } catch (Exception e) {
            message = "L'email ou le numéro de téléphone est déjà utilisé";
        }
        return "redirect:/clients";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {

        try {
            clientRepository.deleteById(id);
        } catch (Exception e) {
            message = "Erreur lors de la suppression du client!";
        }

        return "redirect:/clients";
    }
}
