package org.exampilistic.engines.junit.javatests;

import org.exampilistic.engines.junit.TestLoader;
import org.junit.platform.engine.TestDescriptor;

import java.util.Collection;

public class JavaTestLoader implements TestLoader {
	@Override
	public Collection<TestDescriptor> loadAllTests() {
		return null;
	}
}
