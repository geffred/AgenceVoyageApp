package com.voyage.agence.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Table(name = "clients")
@Entity
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    @NotEmpty(message = "Ce champs ne doit pas être vide ")
    @NotNull(message = "Ce champs ne doit pas être null ")
    private String nom;

    @Column(name = "prenom")
    @NotEmpty(message = "Ce champs ne doit pas être vide ")
    @NotNull(message = "Ce champs ne doit pas être null ")
    private String prenom;

    @Column(name = "email")
    @NotEmpty(message = "Ce champs ne doit pas être vide ")
    @NotNull(message = "Ce champs ne doit pas être null ")
    @Email(message = "veillez entrer une adresse email valide ")
    private String email;

    @Column(name = "telephone")
    @NotEmpty(message = "Ce champs ne doit pas être vide ")
    @NotNull(message = "Ce champs ne doit pas être null ")
    @Pattern(regexp = "^(\\+32|0)[1-9](\\d{1,2})?[ ]?\\d{3}[ ]?\\d{3}$", message = "Veuillez entrer un numéro de téléphone belge valide")
    private String telephone;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", updatable = false)
    private List<Reservation> reservationList;

    public Client() {
    }

    public Client(Long id, String nom, String prenom, String email, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

}
