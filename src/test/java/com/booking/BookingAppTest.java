package com.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

import com.booking.domain.Event;
import com.booking.domain.Ticket;
import com.booking.domain.User;
import com.booking.facade.BookingFacade;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookingAppTest {
    @Autowired
    private BookingFacade bookingFacade;

    @Test
    void testMain() {
        try (var mockedSpringApplication = mockStatic(SpringApplication.class)) {
            mockedSpringApplication.when(() -> SpringApplication.run(BookingApp.class, new String[]{})).thenReturn(null);
            BookingApp.main(new String[]{});
            mockedSpringApplication.verify(() -> SpringApplication.run(BookingApp.class, new String[]{}));
        }
    }

    @Test
    void testCreateEvent() {
        Event eventById = bookingFacade.createEvent("demo", LocalDateTime.now());
        assertEquals("demo", eventById.getTitle());
    }

    @Test
    void testCreateTicket() {
        Ticket saved = bookingFacade.bookTicket(1L, 1L, 100, 1);
        Optional<Ticket> ticket = bookingFacade.getTicketById(saved.getId());

        assertTrue(ticket.isPresent());
        assertEquals(1, ticket.get().getCategory());
    }

    @Test
    void testCreateUser() {
        User saved = bookingFacade.createUser("test", "test@test.com");
        Optional<User> user = bookingFacade.getUserById(saved.getId());

        assertTrue(user.isPresent());
        assertEquals("test", user.get().getName());
    }

}