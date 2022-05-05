package com.goldenawards.rest.domain;

import java.util.List;

public class Classification {

	private List<MovieClassification> min;
	private List<MovieClassification> max;

	public Classification() {
		super();
	}

	public Classification(List<MovieClassification> min, List<MovieClassification> max) {
		super();
		this.min = min;
		this.max = max;
	}

	public List<MovieClassification> getMin() {
		return min;
	}

	public void setMin(List<MovieClassification> min) {
		this.min = min;
	}

	public List<MovieClassification> getMax() {
		return max;
	}

	public void setMax(List<MovieClassification> max) {
		this.max = max;
	}

}
