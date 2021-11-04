package org.sid.cinema.entities;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="projectionProj", types= {org.sid.cinema.entities.Projection.class})
public interface ProjectionProj {
	public Long getId();
	public double getPrice();
	public Date getProjectionDate();
	public Room getRoom();
	public Session getSession();
	public Film getFilm();
	public Collection<Ticket> getTickets();
	
}
