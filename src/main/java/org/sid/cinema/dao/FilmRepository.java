package org.sid.cinema.dao;


import org.sid.cinema.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
@RepositoryRestController
public interface FilmRepository extends JpaRepository<Film, Long>{

}
