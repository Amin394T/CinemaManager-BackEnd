package org.sid.cinema.dao;


import org.sid.cinema.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
@RepositoryRestController
public interface SeatRepository extends JpaRepository<Seat, Long>{

}
