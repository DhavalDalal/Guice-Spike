package com.tsys.parts.part_b;

public class DefaultPartB implements PartB {

	public DefaultPartB(String url, int threads) {
	}

	@Override
	public void call() {
		System.out.println("Default Part B Called!");
	}

}
