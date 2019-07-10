package com.tsys.product;

import java.util.logging.Logger;
import javax.inject.*;

import com.tsys.catalog.aop.Time;
//import com.google.inject.Inject;
//import com.google.inject.name.Named;
import com.tsys.parts.part_a.PartA;
import com.tsys.parts.part_b.PartB;
import com.tsys.parts.part_c.PartC;

public class Aggregate {
	private PartA part_a;
	private PartB part_b;

	// Implicit Injection
	// Guice implicitly injects general purpose components like the 
	// Injector and an instance of java.util.Logger, among others. 
	// Notice we are using loggers all through the samples 
	// but you won’t find an actual binding for them.
	private final Logger logger;
	
	// Field Injection
	@Inject
	private PartC part_c;

	// Constructor Injection
	@Inject
	public Aggregate(PartA part_a, Logger logger) {
		System.out.println("Creating Aggregate...");
		this.part_a = part_a;
		this.logger = logger;
		System.out.println("Logger = " + logger);
		logger.info("Creating Aggregate...");
	}

	// Setter Injection
	@Inject 
	public void setPartB(@Named("Special PartB") PartB part_b) {
		logger.info("setPartB with Special PartB");
		this.part_b = part_b;
	}
	
	@Time
	public void run() {
		System.out.println("Running Aggregate...");
		System.out.println("Calling Part A..." + part_a);
		part_a.call();
		System.out.println("Calling Part B..." + part_b);
		part_b.call();
		System.out.println("Calling Part C..." + part_c);
		part_c.call();
	}
	
	// JIT Injection (named annotation bug???)
	// This runs the runWith() method without explicitly
	// calling it...
	@Inject
	public void runWith(@Named("Special PartA") PartA part_a) {
		System.out.println("Aggregate.runWith(Special PartA)");
		logger.info("Aggregate.runWith(Special PartA)");
		System.out.println("Calling Part A..." + part_a);
		part_a.call();
		System.out.println("Calling Part B..." + part_b);
		part_b.call();
		System.out.println("Calling Part C..." + part_c);
		part_c.call();
	}

}
