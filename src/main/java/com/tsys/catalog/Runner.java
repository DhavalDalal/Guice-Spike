package com.tsys.catalog;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tsys.product.Aggregate;
import com.tsys.product.AnotherAggregate;

public class Runner {
	public static void main(String[] args) {
		try {
			System.out.println("Starting Google Guice Container...");
			Injector injector = Guice.createInjector(new CatalogModule(), new AOPModule());
			System.out.println("Got Injector...");
			System.out.println("Got Application, starting it...");
			Aggregate aggregate = injector.getInstance(Aggregate.class);
			aggregate.run();
			System.out.println("*** AnotherAggregate ***");
			AnotherAggregate aa = injector.getInstance(AnotherAggregate.class);
			System.out.println(aa.execute());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
