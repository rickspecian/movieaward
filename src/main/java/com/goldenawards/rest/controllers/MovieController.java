package com.goldenawards.rest.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.goldenawards.rest.helper.MovieHelper;
import com.goldenawards.rest.models.Classification;
import com.goldenawards.rest.models.Movie;
import com.goldenawards.rest.services.MovieService;

@RestController
@RequestMapping(path = "/movie")
public class MovieController {
	@Autowired
	private MovieService service;

	private MovieHelper controller = new MovieHelper();

	@GetMapping(value = "/{id}")
	public ResponseEntity<Movie> findMovieById(@PathVariable Integer id) {
		try {
			Movie movie = service.findById(id);
			return ResponseEntity.ok().body(movie);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}

	}

	@GetMapping(value = "/classification", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Classification>> fetchAllWinners() {
		List<Classification> classification = new ArrayList<>();
		classification = controller.classifyWinnerList(service.findByWinnerTrue());
		return ResponseEntity.ok().body(classification);
	}

	@GetMapping(value = "/")
	public ResponseEntity<List<Movie>> listAllMovies() {
		List<Movie> listMovie = service.findAll();
		return ResponseEntity.ok().body(listMovie);
	}

	@PostMapping
	public ResponseEntity<Movie> insertMovieOnList(@RequestBody Movie movie) {
		movie = service.create(movie);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(movie.getId()).toUri();
		return ResponseEntity.created(uri).body(movie);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteMovieFromList(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Movie> updateMovieFromList(@PathVariable Integer id, @RequestBody Movie obj) {
		Movie newObj;
		try {
			newObj = service.update(id, obj);
			return ResponseEntity.ok().body(newObj);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}

	}
}
