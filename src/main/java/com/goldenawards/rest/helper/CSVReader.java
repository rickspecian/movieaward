package com.goldenawards.rest.helper;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import com.goldenawards.rest.domain.MovieEntity;

public class CSVReader {

	private static Scanner input;
	private static ArrayList<MovieEntity> valores = new ArrayList<MovieEntity>();

	public ArrayList<MovieEntity> loader(String file) {

		try {
			input = new Scanner(Paths.get(file));

			input.nextLine();

			while (input.hasNext()) {
				String[] data = input.nextLine().split(";");
				if (data.length > 0) {
					MovieEntity movie = new MovieEntity();

					movie.setYear(Integer.parseInt(data[0]));
					movie.setTitle(data[1]);
					movie.setStudios(data[2]);
					movie.setProducers(data[3]);
					boolean winner = false;
					if (data.length == 5) {
						if ("yes".equalsIgnoreCase(data[4])) {
							winner = true;
						}
					}

					movie.setWinner(winner);

					valores.add(movie);
				}
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

		return valores;
	}

}
