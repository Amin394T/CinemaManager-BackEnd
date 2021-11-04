package org.sid.cinema.dao;


import org.sid.cinema.entities.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
@RepositoryRestController
public interface ProjectionRepository extends JpaRepository<Projection, Long>{

}
