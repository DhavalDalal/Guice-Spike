package com.tsys.catalog;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.tsys.catalog.aop.Time;
import com.tsys.catalog.aop.TimingAspect;
import com.tsys.parts.part_a.*;
import com.tsys.parts.part_b.*;
import com.tsys.parts.part_c.DefaultPartC;
import com.tsys.parts.part_c.PartC;

// Scoping in Guice
// Guice supports the scopes and scoping mechanisms we have grown 
// used to in other DI frameworks. Guice defaults to providing a new 
// instance of a defined dependency.
// Singleton and Eager Singleton
// =============================
// The in(Scopes.SINGLETON) specifies that any field with type PartA 
// will get a singleton injected. This singleton is lazily initiated 
// by default.
// The asEagerSingleton() call defines the singleton as eagerly 
// instantiated.
public class CatalogModule extends AbstractModule {

	@Override
	public void configure() {
		// Standard binding
		bind(PartA.class)
		  .to(DefaultPartA.class)
		  .in(Scopes.SINGLETON);
		
		// Named Binding
		bind(PartB.class)
		  .annotatedWith(Names.named("Special PartB"))
		  .to(SpecialPartB.class)
		  .asEagerSingleton();
		
		// Constructor Binding
        // Inject a dependency that doesn’t have a default no-arg constructor 
		// using constructor binding:
        // This untargeted binding will be supplied to any constructor in the 
		// binding that accepts a boolean parameter.
		bind(Boolean.class).toInstance(true);
        // This will inject an instance of DefaultPartC using the 
        // constructor that takes a boolean argument. 
        // We supply the true argument to the constructor by defining an untargeted 
		// binding (defined above) of the Boolean class.
		try {
			bind(DefaultPartC.class).toConstructor(DefaultPartC.class.getConstructor(Boolean.TYPE));
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		bind(PartC.class).to(DefaultPartC.class);

		bind(PartA.class)
		  .annotatedWith(Names.named("Special PartA"))
		  .to(SpecialPartA.class);
		
	}
	
	// Use default Part B
	@Provides
	PartB provideDefaultPartB() {
		// TODO: Inject from properties file
	    return new DefaultPartB("jdbc:mysql://localhost/pizza", 30);
	}
	
//	@Provides
//	PartA provideSpecialPartA() {
//	    return new SpecialPartA();
//	}
	

}
