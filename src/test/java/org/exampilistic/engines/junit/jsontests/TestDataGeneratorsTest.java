package org.exampilistic.engines.junit.jsontests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestDescriptor;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.*;

class TestDataGeneratorsTest {
	private TestDataGenerators testDataGenerators;
	private GeneratorTypes generatorTypes;

	@BeforeEach
	public void setup() {
		generatorTypes = mock(GeneratorTypes.class);
		testDataGenerators = new TestDataGenerators(generatorTypes);
	}

	@Test
	public void getsGeneratorTypeFromGeneratorTypes() {
		Map<String, Object> jsonData = Map.of("type", "some-type");
		TestDataGenerator generator = mock(TestDataGenerator.class);
		TestDescriptor expectedTestDescriptor = mock(TestDescriptor.class);
		when(generator.generate(any())).thenReturn(expectedTestDescriptor);
		when(generatorTypes.generatorFor("some-type")).thenReturn(generator);

		TestDescriptor testDescriptor = testDataGenerators.generate(jsonData);

		assertThat(testDescriptor).isEqualTo(expectedTestDescriptor);
	}

	//TODO add tests for validating the JSON
}