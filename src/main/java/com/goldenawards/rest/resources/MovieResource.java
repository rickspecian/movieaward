package com.goldenawards.rest.resources;

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

import com.goldenawards.rest.controllers.MovieController;
import com.goldenawards.rest.domain.Classification;
import com.goldenawards.rest.domain.Movie;
import com.goldenawards.rest.services.MovieService;

@RestController
@RequestMapping(path = "/movie")
public class MovieResource {
	@Autowired
	private MovieService service;

	private MovieController controller = new MovieController();

	@GetMapping(value = "/{id}")
	public ResponseEntity<Movie> findMovieById(@PathVariable Integer id) {
		Movie obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
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
	public ResponseEntity<Movie> insertMovieOnList(@RequestBody Movie obj) {
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteMovieFromList(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Movie> updateMovieFromList(@PathVariable Integer id, @RequestBody Movie obj) {
		Movie newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
}
