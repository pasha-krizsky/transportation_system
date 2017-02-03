package com.forms;

import com.objects.Ticket;

/**
 * This class defines a model for buy ticket form. (MVC)
 */
public class TicketForm {
    /** Ticket to show */
    Ticket ticket;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
