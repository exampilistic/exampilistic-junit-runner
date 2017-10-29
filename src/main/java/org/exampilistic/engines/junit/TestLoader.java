package org.exampilistic.engines.junit;

import org.junit.platform.engine.TestDescriptor;

import java.util.Collection;

public interface TestLoader {
	Collection<TestDescriptor> loadAllTests();
}
