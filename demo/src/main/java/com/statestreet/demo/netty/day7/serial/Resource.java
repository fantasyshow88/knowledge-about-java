package com.statestreet.demo.netty.day7.serial;

import com.statestreet.demo.netty.day7.serial.core.Serializer;

public class Resource extends Serializer {
	
	private int gold;
	

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	@Override
	protected void read() {
		this.gold = readInt();
	}

	@Override
	protected void write() {
		writeInt(gold);
	}

}
