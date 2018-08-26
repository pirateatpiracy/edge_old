package com.edge.microservices.limitsservice.bean;

public class LimitConfiguration {
	private int minimum;
	private int maximum;
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public LimitConfiguration(int maximum, int minimum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}
	protected LimitConfiguration() {
		super();
	}
	

}
