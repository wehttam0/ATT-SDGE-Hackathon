package com.cognition.xml.parser.model;

import java.util.List;

import com.cognition.xml.parser.Content;

public class IntervalBlock extends Content {
	private int id;
	private Interval interval;
	private List<IntervalReading> listIntervalReading;
	
	public IntervalBlock(){
	}

	public Interval getInterval() {
		return interval;
	}
	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	public List<IntervalReading> getListIntervalReading() {
		return listIntervalReading;
	}
	public void setListIntervalReading(List<IntervalReading> listIntervalReading) {
		this.listIntervalReading = listIntervalReading;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder()
		.append("Duration: ")
		.append(interval.getDuration())
		.append(" - ")
		.append("Start: ")
		.append(interval.getStart());
		return sb.toString();
	}
}
