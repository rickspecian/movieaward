package com.goldenawards.rest.models;

import java.io.Serializable;

public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer year;

	private String title;

	private String studios;

	private String producers;

	private boolean winner;

	public Movie() {

	}

	public Movie(Integer id, Integer year, String title, String studios, String producers, boolean winner) {
		super();
		this.id = id;
		this.year = year;
		this.title = title;
		this.studios = studios;
		this.producers = producers;
		this.winner = winner;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudios() {
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

}
