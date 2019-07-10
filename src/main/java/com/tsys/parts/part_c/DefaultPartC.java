package com.tsys.parts.part_c;

public class DefaultPartC implements PartC {

	private boolean isEnabled;

	public DefaultPartC(boolean isEnabled) {
		this.isEnabled = isEnabled;
		if (this.isEnabled)
			System.out.println("Default Part C Enabled");
		else
			System.out.println("Default Part C Disabled");

	}

	@Override
	public void call() {
		if (isEnabled)
			System.out.println("Default Part C Called!");
		else
			System.out.println("Default Part C Disabled");
	}
}
