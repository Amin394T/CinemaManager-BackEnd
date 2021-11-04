package org.sid.cinema.dao;


import org.sid.cinema.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
@RepositoryRestController
public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
