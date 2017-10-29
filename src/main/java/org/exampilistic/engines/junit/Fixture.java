package org.exampilistic.engines.junit;

import org.junit.platform.commons.annotation.Testable;

@Testable
public @interface Fixture {
	String value();
}
