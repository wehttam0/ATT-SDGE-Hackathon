package com.cognition.xml.parser.model;

public class IntervalReading {
	private TimePeriod timePeriod;
	private int value;
	
	public IntervalReading() {
	}
	
	public IntervalReading(int value) {
		setValue(value);
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}
	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder()
		.append("Duration: ")
		.append(this.getTimePeriod().getDuration())
		.append(" - ")
		.append("Start: ")
		.append(this.getTimePeriod().getStart())
		.append(" - ")
		.append("values: ")
		.append(getValue());
		return sb.toString();
	}
}
