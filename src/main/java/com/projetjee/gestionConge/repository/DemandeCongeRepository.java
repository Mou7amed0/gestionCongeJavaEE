package com.projetjee.gestionConge.repository;

import com.projetjee.gestionConge.entities.DemandeConge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DemandeCongeRepository extends JpaRepository<DemandeConge,Long> {

    @Query("select d from DemandeConge d where d.salarie.id_salarie in (select s from Salarie s where s.groupe.id_groupe=:group_id)")
    List<DemandeConge> findDemandeCongeByGroup(@Param(value = "group_id") long id);
    @Query("select d from DemandeConge d where d.salarie.id_salarie=:id")
    List<DemandeConge> findDemandeCongeByidSalarie(@Param("id") long id);
}
