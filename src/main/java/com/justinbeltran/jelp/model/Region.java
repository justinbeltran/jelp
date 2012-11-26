package com.justinbeltran.jelp.model;

public class Region {
	private Center center;
	private Span span;

	public Center getCenter() {
		return this.center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

	public Span getSpan() {
		return this.span;
	}

	public void setSpan(Span span) {
		this.span = span;
	}
}
