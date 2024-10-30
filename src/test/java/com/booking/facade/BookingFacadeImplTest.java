package com.booking.facade;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.booking.domain.Event;
import com.booking.domain.Ticket;
import com.booking.domain.User;
import com.booking.service.EventService;
import com.booking.service.TicketService;
import com.booking.service.UserService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BookingFacadeImplTest {

    @Mock
    private UserService userService;

    @Mock
    private EventService eventService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private BookingFacadeImpl bookingFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser_success() {
        Long id = 1L;
        String name = "John";
        String email = "john@example.com";

        User user = new User(id, name, email);
        when(userService.createUser(any(User.class))).thenReturn(user);

        User createdUser = bookingFacade.createUser(name, email);

        assertNotNull(createdUser);
        assertEquals(id, createdUser.getId());
        assertEquals(name, createdUser.getName());
        assertEquals(email, createdUser.getEmail());

        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    void createEvent_success() {
        Long id = 1L;
        String title = "Concert";
        LocalDateTime date = LocalDateTime.now();

        Event event = new Event(id, title, date);
        when(eventService.createEvent(any(Event.class))).thenReturn(event);

        Event createdEvent = bookingFacade.createEvent(title, date);

        assertNotNull(createdEvent);
        assertEquals(id, createdEvent.getId());
        assertEquals(title, createdEvent.getTitle());
        assertEquals(date, createdEvent.getDateTime());

        verify(eventService, times(1)).createEvent(any(Event.class));
    }
    @Test
    void bookTicket_success() {
        Long eventId = 2L;
        Long userId = 3L;
        Integer place = 10;
        Integer category = 1;

        doNothing().when(ticketService).bookTicket(any(Ticket.class));

        Ticket bookedTicket = bookingFacade.bookTicket(eventId, userId, place, category);

        assertNotNull(bookedTicket);
        assertEquals(eventId, bookedTicket.getEventId());
        assertEquals(userId, bookedTicket.getUserId());
        assertEquals(place, bookedTicket.getPlace());
        assertEquals(1, bookedTicket.getCategory());

        verify(ticketService, times(1)).bookTicket(any(Ticket.class));
    }
}