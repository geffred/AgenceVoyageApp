package com.voyage.agence.Controller;

import com.voyage.agence.Entity.Client;
import com.voyage.agence.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public String listClients(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "client/list";
    }

    @GetMapping("/add")
    public String showAddClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "client/add";
    }

    @PostMapping("/add")
    public String addClient(@ModelAttribute Client client) {
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String showEditClientForm(@PathVariable Long id, Model model) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client ID:" + id));
        model.addAttribute("client", client);
        return "client/edit";
    }

    @PostMapping("/edit/{id}")
    public String editClient(@PathVariable Long id, @ModelAttribute Client client) {
        client.setId(id);
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return "redirect:/clients";
    }
}
