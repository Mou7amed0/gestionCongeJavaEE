package com.projetjee.gestionConge.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Fonction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private String description;

    @OneToMany(mappedBy = "fonction",fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Salarie> salaries;

    public Fonction() {
    }

    public Long getId_groupe() {
        return id;
    }

    public void setId_groupe(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Salarie> getSalaries() {
        return salaries;
    }

    public void setSalaries(Collection<Salarie> salaries) {
        this.salaries = salaries;
    }
}
