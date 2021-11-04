package org.sid.cinema.dao;


import org.sid.cinema.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestController
@CrossOrigin("*")
public interface CityRepository extends JpaRepository<City, Long>{

}
