package com.goldenawards.rest.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.goldenawards.rest.domain.Movie;
import com.goldenawards.rest.domain.MovieClassification;
import com.goldenawards.rest.services.MovieService;

@RestController
@RequestMapping(path = "/movie")
public class MovieResource {
	@Autowired
	private MovieService service;

	private MovieController controller = new MovieController();

	@GetMapping(value = "/{id}")
	public ResponseEntity<Movie> findById(@PathVariable Integer id) {
		Movie obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/classification")
	public ResponseEntity<HashMap<String, List<MovieClassification>>> listOpen() {
		HashMap<String, List<MovieClassification>> map = new HashMap<String, List<MovieClassification>>();
		List<MovieClassification> listMovieMin = new ArrayList<MovieClassification>();
		List<MovieClassification> listMovieMax = new ArrayList<MovieClassification>();
		List<Movie> listMovieWinner = service.findAllWinner();

		List<MovieClassification> listClassified = controller.classification(listMovieWinner);

		Comparator<MovieClassification> comparator = Comparator.comparing(MovieClassification::getInterval);
		listMovieWinner.clear();
		listMovieMax.clear();

		listMovieMin.add(listClassified.stream().min(comparator).get());
		listMovieMax.add(listClassified.stream().max(comparator).get());

		map.put("min", listMovieMin);
		map.put("max", listMovieMax);
		return ResponseEntity.ok().body(map);
	}

	@GetMapping(value = "/")
	public ResponseEntity<List<Movie>> listAll() {
		List<Movie> listMovie = service.findAll();
		return ResponseEntity.ok().body(listMovie);
	}

	@PostMapping
	public ResponseEntity<Movie> create(@RequestBody Movie obj) {
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Movie> update(@PathVariable Integer id, @RequestBody Movie obj) {
		Movie newObj = service.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
}
