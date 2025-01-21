package com.voyage.agence.Controller;

import com.voyage.agence.Entity.Reservation;
import com.voyage.agence.Repository.ClientRepository;
import com.voyage.agence.Repository.ReservationRepository;
import com.voyage.agence.Repository.VoyageRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VoyageRepository voyageRepository;

    private String message = "";

    @GetMapping
    public String listReservations(Model model) {
        List<Reservation> reservations = reservationRepository.findAll();
        model.addAttribute("reservations", reservations);
        model.addAttribute("message", message);
        message = "";
        return "Reservation/list";
    }

    @GetMapping("/add")
    public String showAddReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("voyages", voyageRepository.findAll());
        return "Reservation/add";
    }

    @PostMapping("/add")
    public String addReservation(@ModelAttribute @Valid Reservation reservation, Errors errors, Model model) {
        // Vérification des erreurs de validation
        if (errors.hasErrors()) {
            return "Reservation/add";
        }

        // Vérification si une réservation existe déjà pour ce client et ce voyage
        Optional<Reservation> optReservation = reservationRepository.findByClientIdAndVoyageId(
                reservation.getClient().getId(),
                reservation.getVoyage().getId());
        if (optReservation.isPresent()) {
            message = "Le client a déjà effectué cette réservation.";
            return "redirect:/reservations";
        }

        // Enregistrement de la réservation
        reservationRepository.save(reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/edit/{id}")
    public String showEditReservationForm(@PathVariable Long id, Model model) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation ID:" + id));
        model.addAttribute("reservation", reservation);
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("voyages", voyageRepository.findAll());
        return "Reservation/edit";
    }

    @PostMapping("/edit/{id}")
    public String editReservation(@PathVariable Long id, @ModelAttribute @Valid Reservation updatedReservation,
            Errors errors) {

        if (errors.hasErrors()) {
            return "Reservation/edit"; // Afficher le formulaire avec les erreurs
        }

        // Charger l'entité existante depuis la base de données
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        // Appliquer les modifications à l'entité existante
        existingReservation.setPaye(updatedReservation.isPaye());
        existingReservation.setClient(updatedReservation.getClient());
        existingReservation.setVoyage(updatedReservation.getVoyage());
        // Appliquer d'autres modifications nécessaires

        // Sauvegarder l'entité mise à jour
        reservationRepository.save(existingReservation);

        return "redirect:/reservations"; // Redirection après la mise à jour
    }

    @GetMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
        return "redirect:/reservations";
    }

    @GetMapping("/filter")
    public String filterReservations(
            @RequestParam(required = false) Boolean paye,
            @RequestParam(name = "nom", required = false) String nom,
            Model model) {

        List<Reservation> reservations;

        if (paye != null && nom != null) {
            // Filtrer par "paye" et "nom" combinés
            reservations = reservationRepository.findByPayeAndClientNomContaining(paye, nom);
        } else if (paye != null) {
            // Filtrer uniquement par "paye"
            reservations = reservationRepository.findByPaye(paye);
        } else if (nom != null) {
            // Filtrer uniquement par "nom"
            reservations = reservationRepository.findByClientNomContaining(nom);
        } else {
            // Pas de filtres, récupérer toutes les réservations
            reservations = reservationRepository.findAll();
        }

        model.addAttribute("reservations", reservations);
        return "Reservation/list";
    }

}
