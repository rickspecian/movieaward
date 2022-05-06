package com.goldenawards.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MovieApplicationTests {

	@Autowired
	private MockMvc mvc;

	private String body = "{\"year\":1985,\"title\": \"testado\",\"studios\":\"todos\",\"producers\":\"nenhum\",\"winner\":true}";

	@Test
	public void givenMovieIdWhenGetMovieByIdThenReturnMovie() throws Exception {
		mvc.perform(get("/movie/50").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void givenMovieListWhenListAllMoviesThenReturnListMovie() throws Exception {
		mvc.perform(get("/movie/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void deleteMovieIdWhenDeleteMovieFromListThenReturnNoContent() throws Exception {
		mvc.perform(delete("/movie/50").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
	}

	@Test
	void insertMovieWhenInsertMovieOnListThenReturnOk() throws Exception {
		mvc.perform(post("/movie/").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().isCreated());
	}

	@Test
	public void updateMovieIdWhenUpdateMovieFromListThenReturnOk() throws Exception {
		mvc.perform(put("/movie/150").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isOk());
	}

	@Test
	public void givenListWinnerFetchAllWinnersThenReturnOk() throws Exception {
		mvc.perform(get("/movie/classification").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
