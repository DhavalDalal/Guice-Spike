package com.tsys.catalog.aop;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// Aspect Oriented Programming in Guice
// Guice is compliant with the AOPAlliance’s specifications for 
// aspect-oriented programming. 
// Let us implement a TimingAspect, which we will use to log time
// taken by a method to execute.
// Step 1: Implement the AOPAlliance’s MethodInterceptor
// Step 2: Define a plain java annotation
// Step 3: Define binding for a Matcher (see AOPModule.java)
// Step 4: Apply our annotation to methods in our classes (See Aggregate.java 
//         and AnotherAggregate.java) and load our 
//         Module (AOPModule - see Runner.java)
public class TimingAspect implements MethodInterceptor {
	
	private final Logger log;
	
	@Inject
	public TimingAspect(Logger log) {
		this.log = log;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
//		Object[] arguments = invocation.getArguments();
		long startTime = System.currentTimeMillis();
		final Object result = invocation.proceed();
		long timeTaken = System.currentTimeMillis() - startTime;
		log.info(String.format("Method %s() took %d (ms)", invocation.getMethod().getName(), timeTaken));
		return result;
	}
}