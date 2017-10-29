package org.exampilistic.engines.junit.jsontests;

import org.junit.platform.engine.TestDescriptor;

import java.util.Map;

public class TestDataGenerators {
	private final GeneratorTypes generatorTypes;

	public TestDataGenerators() {
		this(new DefaultGeneratorTypes());
	}

	public TestDataGenerators(GeneratorTypes generatorTypes) {
		this.generatorTypes = generatorTypes;
	}

	public TestDescriptor generate(Map<String, Object> jsonData) {
		TestDataGenerator generator = generatorTypes.generatorFor((String) jsonData.get("type"));
		return generator.generate(jsonData);
	}
}
