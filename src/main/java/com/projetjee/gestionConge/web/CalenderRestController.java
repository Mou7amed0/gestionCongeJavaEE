package com.projetjee.gestionConge.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetjee.gestionConge.entities.Event;
import com.projetjee.gestionConge.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/event")
public class CalenderRestController {
    private final IEventService iEventService;
    @Autowired
    public CalenderRestController(IEventService iEventService) {
        this.iEventService = iEventService;
    }

    @GetMapping(value = "/all")
    public List<Event> getEvents() {
        String jsonMsg = null;

            List<Event> events =iEventService.listEvents();


            //FullCalendar pass encoded string
//            ObjectMapper mapper = new ObjectMapper();
//            jsonMsg =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(events);

        return events;
    }
}
