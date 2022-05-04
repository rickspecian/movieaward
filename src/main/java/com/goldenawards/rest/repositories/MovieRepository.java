package com.goldenawards.rest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.goldenawards.rest.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	@Query("SELECT obj FROM Movie obj where obj.winner = true")
	List<Movie> findAllWinner();

}
