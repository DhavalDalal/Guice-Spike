package com.tsys.catalog;

import java.util.logging.Logger;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.tsys.catalog.aop.Time;
import com.tsys.catalog.aop.TimingAspect;

public class AOPModule extends AbstractModule {

	@Override
	protected void configure() {
		// Matcher is a Guice class that we use do specify the components
		// that our AOP annotation will apply to. In this case, we want
		// the annotation to apply to implementations of CommunicationMode:
		bindInterceptor(
			Matchers.any(), 
			Matchers.annotatedWith(Time.class), 
			new TimingAspect(Logger.getLogger("TimingAspect")));
	}
}