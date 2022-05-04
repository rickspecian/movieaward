package com.goldenawards.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.goldenawards.rest.domain.Movie;
import com.goldenawards.rest.repositories.MovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
class MovieApplicationTests {

	@Autowired
	private MovieRepository movieRepository;
	@Rule
	private ExpectedException thrown = ExpectedException.none();

	@Test
	public void createShoudPersistDate() {
		Movie movie = new Movie(1985, "Teste filme 1", "Studio 1", "produtor 1, produtor 2 and produtor 3", true);
		this.movieRepository.save(movie);
		assertThat(movie.getYear()).isEqualTo(1985);
		assertThat(movie.getTitle()).isEqualTo("Teste filme 1");
		assertThat(movie.getStudios()).isEqualTo("Studio 1");
		assertThat(movie.getProducers()).isEqualTo("produtor 1, produtor 2 and produtor 3");
		assertThat(movie.isWinner()).isEqualTo(true);
	}

	@Test
	public void deleteShoudRemoveDate() {
		Movie movie = new Movie(1985, "Teste filme 1", "Studio 1", "produtor 1, produtor 2 and produtor 3", true);
		this.movieRepository.save(movie);
		movieRepository.delete(movie);
		assertThat(this.movieRepository.findById(movie.getId())).isEmpty();
	}

	@Test
	public void updateShoudChangeDate() {
		Movie movie = new Movie(1985, "Teste filme 1", "Studio 1", "produtor 1, produtor 2 and produtor 3", true);
		this.movieRepository.save(movie);
		movie.setYear(1990);
		movie.setTitle("Teste update filme 1");
		movie.setStudios("Teste update Studio 1");
		movie.setProducers("produtor 1 and produtor 3");
		movie.setWinner(false);
		movie = this.movieRepository.save(movie);
		assertThat(movie.getTitle()).isEqualTo("Teste update filme 1");
		assertThat(movie.getStudios()).isEqualTo("Teste update Studio 1");
		assertThat(movie.getProducers()).isEqualTo("produtor 1 and produtor 3");
		assertThat(movie.isWinner()).isEqualTo(false);
	}

	@Test
	public void listShoudHaveDate() {
		Movie movie1 = new Movie(1985, "Teste filme 1", "Studio 1", "produtor 1, produtor 2 and produtor 3", true);
		Movie movie2 = new Movie(1990, "Teste filme 2", "Studio 2", "produtor 4, produtor 5 and produtor 6", false);
		this.movieRepository.save(movie1);
		this.movieRepository.save(movie2);
		List<Movie> movieList = this.movieRepository.findAll();
		assertThat(movieList.size()).isEqualTo(2);
	}
}
