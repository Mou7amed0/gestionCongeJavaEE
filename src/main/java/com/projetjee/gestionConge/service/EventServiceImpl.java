package com.projetjee.gestionConge.service;

import com.projetjee.gestionConge.entities.Event;
import com.projetjee.gestionConge.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements IEventService{
    private final EventRepository eventRepository;
    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event getEventById(Long id) {
        return null;
    }

    @Override
    public List<Event> listEvents() {
        return eventRepository.findAll();
    }
}
