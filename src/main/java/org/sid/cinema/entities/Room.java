package org.sid.cinema.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Room implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50)
	private String name;
	private int numSeats;
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Cinema cinema;
	@OneToMany(mappedBy = "room")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Seat> seats;
	@OneToMany(mappedBy = "room")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Projection> projections;
}
