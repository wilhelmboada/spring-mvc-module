package com.booking.service;

import com.booking.dao.TicketRepository;
import com.booking.domain.Ticket;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    private final TicketRepository ticketDao;

    public TicketService(TicketRepository ticketDao) {
        this.ticketDao = ticketDao;
    }

    public void bookTicket(Ticket ticket) {
        logger.info("booking ticket: {}", ticket);
        ticketDao.save(ticket);
    }

    public Ticket getTicketById(Long id) {
        logger.info("get ticket by id: {}", id);
        return ticketDao.findById(id).orElse(null);
    }

    public List<Ticket> getAllTickets() {
        return ticketDao.findAll();
    }

    public Page<Ticket> searchTickets(Long userId, Pageable pageable) {
        return ticketDao.findAllByUserId(userId, pageable);
    }
}
