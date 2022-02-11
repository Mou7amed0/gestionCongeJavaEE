package com.projetjee.gestionConge.repository;

import com.projetjee.gestionConge.entities.Salarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SalarieRepository extends JpaRepository<Salarie,Long> {
    Salarie findByLogin(String username);

    @Query("select s from Salarie s where s.login.username=:username ")
    Salarie findByIdLogin(@Param(value="username") String username);
}
