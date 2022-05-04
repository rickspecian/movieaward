package com.goldenawards.rest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldenawards.rest.domain.Movie;
import com.goldenawards.rest.repositories.MovieRepository;
import com.goldenawards.rest.services.exceptions.ObjectNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	public Movie findById(Integer id) {
		Optional<Movie> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Movie.class.getName()));
	}

	public List<Movie> findAllWinner() {
		List<Movie> listMovie = repository.findAllWinner();
		return listMovie;
	}

	public List<Movie> findAll() {
		List<Movie> listMovie = repository.findAll();
		return listMovie;
	}

	public Movie create(Movie obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public Movie update(Integer id, Movie obj) {
		Movie newObj = findById(id);
		newObj.setYear(obj.getYear());
		newObj.setStudios(obj.getStudios());
		newObj.setTitle(obj.getTitle());
		newObj.setProducers(obj.getProducers());
		newObj.setWinner(obj.isWinner());
		return repository.save(newObj);
	}

}
