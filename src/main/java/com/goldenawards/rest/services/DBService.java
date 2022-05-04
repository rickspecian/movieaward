package com.goldenawards.rest.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.goldenawards.rest.controllers.CSVReader;
import com.goldenawards.rest.domain.Movie;
import com.goldenawards.rest.repositories.MovieRepository;

@Service
public class DBService {

	@Autowired
	private MovieRepository movieRepository;

	@Bean
	public void instanceDatabase() {
		ArrayList<Movie> valores = new CSVReader().loader("C:\\Estudo\\MovieAward\\csv\\movielist.csv");

		movieRepository.saveAll(valores);
	}

}