package com.goldenawards.rest.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.goldenawards.rest.domain.MovieEntity;
import com.goldenawards.rest.helper.CSVReader;
import com.goldenawards.rest.repositories.MovieRepository;

@Service
public class DBService {

	@Autowired
	private MovieRepository movieRepository;

	@Bean
	public void instanceDatabase() {

		ArrayList<MovieEntity> movies = new CSVReader()
				.loader(System.getProperty("user.home") + System.getProperty("file.separator") + "movielist.csv");

		movieRepository.saveAll(movies);
	}

}
