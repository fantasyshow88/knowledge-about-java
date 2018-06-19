package com.roocon.sharding03;

public class Bean {

	private Integer max;

	private Integer min;

	private Integer sharding;

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getSharding() {
		return sharding;
	}

	public void setSharding(Integer sharding) {
		this.sharding = sharding;
	}

	@Override
	public String toString() {
		return "Bean [max=" + max + ", min=" + min + ", sharding=" + sharding + "]";
	}

}
