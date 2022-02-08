package com.projetjee.gestionConge.repository;

import com.projetjee.gestionConge.entities.DemandeConge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DemandeCongeRepository extends JpaRepository<DemandeConge,Long> {

    @Query("SELECT * FROM `demande_conge` WHERE `salarie_id_salarie` in SELECT id_salarie FROM `salarie` WHERE `groupe_id_groupe`=:idGroupe ")
    List<DemandeConge> findByGroupe(@Param("idGroupe")Long idGroupe);

}
