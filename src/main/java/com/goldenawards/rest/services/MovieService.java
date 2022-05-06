package com.goldenawards.rest.services;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldenawards.rest.domain.MovieEntity;
import com.goldenawards.rest.models.Movie;
import com.goldenawards.rest.repositories.MovieRepository;
import com.goldenawards.rest.services.exceptions.ObjectNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	public Movie findById(Integer id) throws IllegalAccessException, InvocationTargetException {
		MovieEntity movieEntity = repository.findById(id).get();
		if (movieEntity == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Movie.class.getName());
		}
		Movie movie = new Movie(movieEntity.getId(), movieEntity.getYear(), movieEntity.getTitle(),
				movieEntity.getStudios(), movieEntity.getProducers(), movieEntity.isWinner());
		return movie;
	}

	public List<Movie> findByWinnerTrue() {
		List<MovieEntity> movieListEntity = repository.findByWinnerTrue();
		List<Movie> movieList = new ArrayList<>();
		for (MovieEntity movieEntity : movieListEntity) {
			movieList.add(new Movie(movieEntity.getId(), movieEntity.getYear(), movieEntity.getTitle(),
					movieEntity.getStudios(), movieEntity.getProducers(), movieEntity.isWinner()));
		}
		return movieList;
	}

	public List<Movie> findAll() {
		List<MovieEntity> movieListEntity = repository.findAll();
		List<Movie> movieList = new ArrayList<>();
		for (MovieEntity movieEntity : movieListEntity) {
			movieList.add(new Movie(movieEntity.getId(), movieEntity.getYear(), movieEntity.getTitle(),
					movieEntity.getStudios(), movieEntity.getProducers(), movieEntity.isWinner()));
		}
		return movieList;
	}

	public Movie create(Movie movie) {
		movie.setId(null);
		MovieEntity movieEntity = new MovieEntity(movie.getYear(), movie.getTitle(), movie.getStudios(),
				movie.getProducers(), movie.isWinner());
		repository.save(movieEntity);
		movie.setId(movieEntity.getId());
		return movie;
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public Movie update(Integer id, Movie movie) throws IllegalAccessException, InvocationTargetException {
		Movie newObj = findById(id);
		newObj.setYear(movie.getYear());
		newObj.setStudios(movie.getStudios());
		newObj.setTitle(movie.getTitle());
		newObj.setProducers(movie.getProducers());
		newObj.setWinner(movie.isWinner());

		MovieEntity movieEntity = new MovieEntity(newObj.getId(), newObj.getYear(), newObj.getTitle(),
				newObj.getStudios(), newObj.getProducers(), newObj.isWinner());

		repository.save(movieEntity);
		return newObj;
	}

}
