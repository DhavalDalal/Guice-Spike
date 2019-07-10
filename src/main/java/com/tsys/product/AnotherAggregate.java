package com.tsys.product;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import com.tsys.catalog.aop.Time;
import com.tsys.parts.part_a.PartA;
import com.tsys.parts.part_b.PartB;

public class AnotherAggregate {
	private final Logger log;
	private final PartA part_a;
	private final PartB part_b;
	
	@Inject
	public AnotherAggregate(PartA part_a, @Named("Special PartB") PartB part_b, Logger log) {
		this.part_a = part_a;
		this.part_b = part_b;
		this.log = log;
	}

	@Time
	public String execute() throws InterruptedException {
		log.info("AnotherAggregate.execute() Calling PartA = " + part_a);
		part_a.call();
		Thread.sleep(1000);
		log.info("AnotherAggregate.execute() Calling PartB = " + part_b);
		part_b.call();
		return "Done";
	}
}
