package com.voyage.agence.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "transports")
@Entity
public class Transport {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_transport")
    @NotNull(message = "Le type de transport ne doit pas être null.")
    private TypeTransport typeTransport;

    @Column(name = "compagnie")
    @NotEmpty(message = "La compagnie ne doit pas être vide.")
    private String compagnie;

    @Column(name = "heure_depart")
    @NotNull(message = "L'heure de départ est obligatoire.")
    private LocalTime heureDepart;

    @Column(name = "heure_arrivee")
    @NotNull(message = "L'heure d'arrivée est obligatoire.")
    private LocalTime heureArrivee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "La date de départ doit être dans le futur ou aujourd'hui.")
    @Column(name = "date_depart")
    @NotNull(message = "La date de départ est obligatoire.")
    private LocalDate dateDepart;

    @FutureOrPresent(message = "La date d'arrivée doit être dans le futur ou aujourd'hui.")
    @Column(name = "date_arrivee")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La date d'arrivée est obligatoire.")
    private LocalDate dateArrivee;

    @OneToMany(mappedBy = "transport", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voyage> voyages = new ArrayList<>();

    public Transport() {
    }

    public Transport(Long id,
            TypeTransport typeTransport, String compagnie, LocalTime heureDepart, LocalTime heureArrivee,
            LocalDate dateDepart, LocalDate dateArrivee) {
        this.id = id;
        this.typeTransport = typeTransport;
        this.compagnie = compagnie;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeTransport getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(TypeTransport typeTransport) {
        this.typeTransport = typeTransport;
    }

    public String getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public List<Voyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<Voyage> voyages) {
        this.voyages = voyages;
    }

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(LocalDate dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }
}
