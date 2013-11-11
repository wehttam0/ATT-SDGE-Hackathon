package com.cognition.xml.parser.model;

public class TimePeriod {
	private long duration;
	private long start;
	
	public TimePeriod(long duration, long start) {
		setDuration(duration);
		setStart(start);
	}

	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
}
