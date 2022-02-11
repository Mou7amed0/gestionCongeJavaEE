package com.projetjee.gestionConge.repository;

import com.projetjee.gestionConge.entities.Salarie;
import org.springframework.data.jpa.repository.JpaRepository;
import com.projetjee.gestionConge.entities.Login;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findLoginByUsername(String username);


}
