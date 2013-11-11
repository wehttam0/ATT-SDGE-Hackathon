package com.cognition.xml.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.cognition.xml.parser.model.Interval;
import com.cognition.xml.parser.model.IntervalBlock;
import com.cognition.xml.parser.model.IntervalReading;
import com.cognition.xml.parser.model.TimePeriod;

public class XmlParser {
	Map<Integer, IntervalBlock> mp = new HashMap<Integer, IntervalBlock>();
	String tagContent = null;
	List<IntervalBlock> blockIntervals = new ArrayList<IntervalBlock>();
	List<IntervalReading> readingIntervals = new ArrayList<IntervalReading>();

	public static void main(String[] args) {
		XmlParser xParser = new XmlParser();
		try {
			xParser.proceesDocument();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

	public void proceesDocument() throws XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory
				.createXMLStreamReader(ClassLoader
						.getSystemResourceAsStream(
				"com/cognition/H_SAMPLE_99999_CONSUMPTION_20120914081520.xml"));
				//"com/my/xml/D_SAMPLE_99999_CONSUMPTION_20120914143023.xml"));

		processIntervalBlock(reader);

		for(IntervalReading lir : mp.get(1).getListIntervalReading()) {
			System.out.println(lir);
		} 
	}

	public void processIntervalBlock(XMLStreamReader reader)
			throws XMLStreamException {
		IntervalBlock intervalBlock = new IntervalBlock();
		IntervalReading intervalReading = new IntervalReading();
		Interval interval = null;
		TimePeriod timePeriod = null;
		QName qname = null;
		int duration = 0;
		int start = 0;
		int count = 1;
		while (reader.hasNext()) {
			int event = reader.next();
			switch (event) {
			case XMLStreamConstants.START_ELEMENT:
				qname = reader.getName();
				if ("IntervalReading".equalsIgnoreCase(qname.getLocalPart())) {
					intervalReading = new IntervalReading();
				} else if ("duration".equals(qname.getLocalPart())) {
					duration = Integer.valueOf(reader.getElementText());
				} else if ("start".equals(qname.getLocalPart())) {
					start = Integer.valueOf(reader.getElementText());
				} else if ("value".equals(qname.getLocalPart())) {
					intervalReading.setValue(Integer.valueOf(reader.getElementText()));
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				qname = reader.getName();
				if ("interval".equals(qname.getLocalPart()) 
						&& duration != 0 && start != 0) {
					interval = new Interval(duration, start);
					intervalBlock.setInterval(interval);
				} else if ("timePeriod".equalsIgnoreCase(qname.getLocalPart()) 
						&& duration != 0 && start != 0) {
					timePeriod = new TimePeriod(duration, start);
					intervalReading.setTimePeriod(timePeriod);
				} else if ("IntervalReading".equalsIgnoreCase(qname.getLocalPart()) 
						&& intervalReading != null) {
					readingIntervals.add(intervalReading);
				} else if ("IntervalBlock".equalsIgnoreCase(qname.getLocalPart()) 
						&& intervalBlock != null) {
					intervalBlock.setListIntervalReading(readingIntervals);
					intervalBlock.setId(count);
					mp.put(count++, intervalBlock);
				}
				break;
			}
		}
	}
	
	public List<IntervalReading> testData() {
		List<IntervalReading> l = new ArrayList<IntervalReading>();
		l.add(new IntervalReading(335));
		l.add(new IntervalReading(330));
		l.add(new IntervalReading(325));
		l.add(new IntervalReading(335));
		return l; 
	}

	public float calcTotalUsageByDay(IntervalBlock ib) {
		float total = 0;
		for (IntervalReading ir : ib.getListIntervalReading()) {
			total += (float) ir.getValue() / 1000;
		}
		return total;
	}
	
	public Map<Integer, IntervalBlock> getMp() {
		return mp;
	}
}
