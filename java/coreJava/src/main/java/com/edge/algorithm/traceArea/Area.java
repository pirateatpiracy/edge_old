package com.edge.algorithm.traceArea;

import java.util.Arrays;
import java.util.List;

public class Area {
	private String bayType;
	private List<Coordinate> coordinate;
	public Area(String bayType, List<Coordinate> coordinate) {
		super();
		this.bayType = bayType;
		this.coordinate = coordinate;
	}
	public String getBayType() {
		return bayType;
	}
	public void setBayType(String bayType) {
		this.bayType = bayType;
	}
	public List<Coordinate> getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(List<Coordinate> coordinate) {
		this.coordinate = coordinate;
	}
	@Override
	public String toString() {
		return "Area [bayType=" + bayType + ", coordinate=" + coordinate + "]";
	}
	
	
}
