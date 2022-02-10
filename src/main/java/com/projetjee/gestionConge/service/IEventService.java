package com.projetjee.gestionConge.service;

import com.projetjee.gestionConge.entities.Event;

import java.util.List;

public interface IEventService {
    Event getEventById(Long id );
    List<Event> listEvents();
}
