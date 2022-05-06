package com.goldenawards.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.goldenawards.rest.controllers.MovieController;
import com.goldenawards.rest.domain.MovieEntity;
import com.goldenawards.rest.repositories.MovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@WebMvcTest(MovieController.class)
class MovieResouceTest {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MockMvc mvc;

	@Test
	public void createShoudPersistDate() {
		MovieEntity movie = new MovieEntity(1985, "Teste filme 1", "Studio 1", "produtor 1, produtor 2 and produtor 3", true);
		this.movieRepository.save(movie);
		assertThat(movie.getYear()).isEqualTo(1985);
		assertThat(movie.getTitle()).isEqualTo("Teste filme 1");
		assertThat(movie.getStudios()).isEqualTo("Studio 1");
		assertThat(movie.getProducers()).isEqualTo("produtor 1, produtor 2 and produtor 3");
		assertThat(movie.isWinner()).isEqualTo(true);
	}

	@Test
	public void deleteShoudRemoveDate() {
		MovieEntity movie = new MovieEntity(1985, "Teste filme 1", "Studio 1", "produtor 1, produtor 2 and produtor 3", true);
		this.movieRepository.save(movie);
		movieRepository.delete(movie);
		assertThat(this.movieRepository.findById(movie.getId())).isEmpty();
	}

	@Test
	public void updateShoudChangeDate() {
		MovieEntity movie = new MovieEntity(1985, "Teste filme 1", "Studio 1", "produtor 1, produtor 2 and produtor 3", true);
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
		MovieEntity movie1 = new MovieEntity(1985, "Teste filme 1", "Studio 1", "produtor 1, produtor 2 and produtor 3", true);
		MovieEntity movie2 = new MovieEntity(1990, "Teste filme 2", "Studio 2", "produtor 4, produtor 5 and produtor 6", false);
		this.movieRepository.save(movie1);
		this.movieRepository.save(movie2);
		List<MovieEntity> movieList = this.movieRepository.findAll();
		assertThat(movieList.size()).isEqualTo(2);
	}

}
