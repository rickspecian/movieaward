package com.goldenawards.rest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.goldenawards.rest.domain.MovieClassification;

public class MovieTest {

	@Test
	void javaStreamMulti() {

		List<MovieClassification> movies = Arrays.asList(new MovieClassification(5), new MovieClassification(9),
				new MovieClassification(3), new MovieClassification(12), new MovieClassification(3));

		List<MovieClassification> listMin = movies.stream()
				.filter(x -> x.getInterval().equals(
						movies.stream().min((z, y) -> z.getInterval().compareTo(y.getInterval())).get().getInterval()))
				.collect(Collectors.toList());

		listMin.stream().forEach(x -> {
			System.out.println(x.getInterval());
		});
	}
}
