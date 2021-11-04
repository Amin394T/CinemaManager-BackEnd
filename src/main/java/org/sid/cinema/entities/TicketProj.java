package org.sid.cinema.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="ticketProj", types=Ticket.class)
public interface TicketProj {
	public Long getId();
	public String getOwnerName();
	public double getPrice();
	public Integer getPayCode();
	public boolean getTaken();
	public Seat getSeat();
}
