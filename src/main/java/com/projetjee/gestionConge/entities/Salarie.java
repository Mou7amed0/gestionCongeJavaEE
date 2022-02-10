package com.projetjee.gestionConge.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Salarie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_salarie;
    private String nom;
    private String prenom;

    private Integer solde;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_embauche;
    @Enumerated(EnumType.STRING)
    private Fonction fonction;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Groupe groupe;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id", referencedColumnName = "id")
    private Login login;
    public Salarie(String nom, String prenom, Login login, Integer solde, LocalDate date_embauche, Fonction fonction, Groupe groupe) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.solde = solde;
        this.date_embauche = date_embauche;
        this.fonction = fonction;
        this.groupe = groupe;
    }

    @OneToMany(mappedBy = "salarie",fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Collection<DemandeConge> demandeConges;

    public Salarie(String nom, String prenom, Login login, Integer solde, LocalDate date_embauche, Fonction fonction) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.solde = solde;
        this.date_embauche = date_embauche;
        this.fonction = fonction;
    }

    public Salarie() {
    }

    public Long getId_salarie() {
        return id_salarie;
    }

    public void setId_salarie(Long id_salarie) {
        this.id_salarie = id_salarie;
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

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Integer getSolde() {
        return solde;
    }

    public void setSolde(Integer solde) {
        this.solde = solde;
    }

    public LocalDate getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(LocalDate date_embauche) {
        this.date_embauche = date_embauche;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public Collection<DemandeConge> getDemandeConges() {
        return demandeConges;
    }

    public void setDemandeConges(Collection<DemandeConge> demandeConges) {
        this.demandeConges = demandeConges;
    }
}
