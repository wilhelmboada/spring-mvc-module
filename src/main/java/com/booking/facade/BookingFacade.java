package com.booking.facade;

import com.booking.domain.Event;
import com.booking.domain.Ticket;
import com.booking.domain.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.xml.bind.JAXBException;
import org.springframework.data.domain.Page;

public interface BookingFacade {
    User createUser(String name, String email);
    Event createEvent(String title, LocalDateTime date);
    Ticket bookTicket(Long eventId, Long userId, Integer place, int category);
    Optional<Ticket> getTicketById(Long ticketId);
    Optional<Event> getEventById(Long eventId);
    void preloadTickets(String path) throws JAXBException, IOException;
    Optional<User> getUserById(Long userId);
    List<Ticket> getAllTickets();
    List<User> getAllUsers();
    Page<Ticket> getBookedTickets(User user, int pageSize, int pageNum);
}