package com.projetjee.gestionConge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetjee.gestionConge.entities.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findLoginByUsername(String username);
}
