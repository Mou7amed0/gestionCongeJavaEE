package com.projetjee.gestionConge.repository;

import com.projetjee.gestionConge.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EventRepository extends JpaRepository<Event,Long> {
}
