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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "transport", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voyage> voyages = new ArrayList<>();

    public Transport() {
    }

    public Transport(Long id,
            TypeTransport typeTransport, String compagnie, LocalTime heureDepart, LocalTime heureArrivee) {
        this.id = id;
        this.typeTransport = typeTransport;
        this.compagnie = compagnie;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
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
}
