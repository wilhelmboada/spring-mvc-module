package com.booking.service;

import com.booking.dao.EventRepository;
import com.booking.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;

    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        logger.info("create event: {}", event.getId());
        eventRepository.save(event);
        return event;
    }

    public Event getEventById(Long id) {
        logger.info("get event by id: {}", id);
        return eventRepository.findEventById(id);
    }

}

