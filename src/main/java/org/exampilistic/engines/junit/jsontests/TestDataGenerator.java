package org.exampilistic.engines.junit.jsontests;

import org.junit.platform.engine.TestDescriptor;

import java.util.Map;

public interface TestDataGenerator {
	TestDescriptor generate(Map<String, Object> testData);
}
