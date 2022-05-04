package com.goldenawards.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.goldenawards.rest.domain.Movie;
import com.goldenawards.rest.domain.MovieClassification;

public class MovieController {

	public HashMap<String, Integer> splitProducers(List<Movie> movie, String type) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		for (Movie mc : movie) {
			String[] producerSplit = mc.getProducers().split(", | and ");
			for (int x = 0; x < producerSplit.length; x++) {
				if (map.get(producerSplit[x]) == null) {
					map.put(producerSplit[x], mc.getYear());
				} else {
					if (map.get(producerSplit[x]) < mc.getYear() && "min".equals(type)) {
						map.put(producerSplit[x], mc.getYear());
					} else if (map.get(producerSplit[x]) > mc.getYear() && "max".equals(type)) {
						map.put(producerSplit[x], mc.getYear());
					}
				}
			}

		}
		return map;
	}

	public List<MovieClassification> classification(List<Movie> listWinner) {
		HashMap<String, Integer> mapMin = this.splitProducers(listWinner, "min");
		HashMap<String, Integer> mapMax = this.splitProducers(listWinner, "max");

		List<MovieClassification> movieClassificationsList = new ArrayList<MovieClassification>();

		for (String producer : mapMin.keySet()) {
			Integer minAno = null;
			Integer maxAno = null;

			if (mapMin.get(producer) < mapMax.get(producer)) {
				minAno = mapMin.get(producer);
				maxAno = mapMax.get(producer);
			} else {
				minAno = mapMax.get(producer);
				maxAno = mapMin.get(producer);
			}

			MovieClassification movieClassification = new MovieClassification();
			movieClassification.setProducer(producer);
			movieClassification.setPreviousWin(minAno);
			movieClassification.setFollowingWin(maxAno);
			movieClassification.setInterval(maxAno - minAno);

			if (movieClassification.getInterval() > 0) {
				movieClassificationsList.add(movieClassification);
			}
		}

		return movieClassificationsList;
	}
}