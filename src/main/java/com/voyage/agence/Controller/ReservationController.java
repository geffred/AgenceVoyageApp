package com.voyage.agence.Controller;

import com.voyage.agence.Entity.Reservation;
import com.voyage.agence.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping
    public String listReservations(Model model) {
        List<Reservation> reservations = reservationRepository.findAll();
        model.addAttribute("reservations", reservations);
        return "reservation/list";
    }

    @GetMapping("/add")
    public String showAddReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation/add";
    }

    @PostMapping("/add")
    public String addReservation(@ModelAttribute Reservation reservation) {
        reservationRepository.save(reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/edit/{id}")
    public String showEditReservationForm(@PathVariable Long id, Model model) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation ID:" + id));
        model.addAttribute("reservation", reservation);
        return "reservation/edit";
    }

    @PostMapping("/edit/{id}")
    public String editReservation(@PathVariable Long id, @ModelAttribute Reservation reservation) {
        reservation.setId(id);
        reservationRepository.save(reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
        return "redirect:/reservations";
    }

    @GetMapping("/filter")
    public String filterReservations(@RequestParam(required = false) Boolean paye, Model model) {
        List<Reservation> reservations;
        if (paye != null) {
            reservations = reservationRepository.findAll().stream().filter(r -> r.isPaye() == paye).toList();
        } else {
            reservations = reservationRepository.findAll();
        }
        model.addAttribute("reservations", reservations);
        return "reservation/list";
    }
}
