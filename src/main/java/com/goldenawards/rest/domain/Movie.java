package com.goldenawards.rest.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue
	@Id
	private Integer id;

	@Column(name = "year")
	private Integer year;

	@Column(name = "title")
	private String title;

	@Column(name = "studios")
	private String studios;

	@Column(name = "producers")
	private String producers;

	@Column(name = "winner")
	private boolean winner;

	public Movie() {

	}

	public Movie(Integer year, String title, String studios, String producers, boolean winner) {
		super();
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
