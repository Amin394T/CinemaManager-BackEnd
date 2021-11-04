package org.sid.cinema.dao;


import org.sid.cinema.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
@RepositoryRestController
public interface RoomRepository extends JpaRepository<Room, Long>{

}
