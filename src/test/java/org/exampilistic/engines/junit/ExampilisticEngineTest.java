package org.exampilistic.engines.junit;

import org.exampilistic.engines.junit.javatests.JavaTestLoader;
import org.exampilistic.engines.junit.jsontests.JsonTestLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.EngineDiscoveryRequest;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.UniqueId;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExampilisticEngineTest {

	private JsonTestLoader jsonTestLoader;
	private JavaTestLoader javaTestLoader;
	private ExampilisticEngine engine;

	@BeforeEach
	public void setup() {
		this.jsonTestLoader = mock(JsonTestLoader.class);
		this.javaTestLoader = mock(JavaTestLoader.class);
		this.engine = new ExampilisticEngine(jsonTestLoader, javaTestLoader);
	}

	@Test
	public void providesEngineId() {
		assertThat(engine.getId()).isEqualTo("exampilistic");
	}

	@Test
	public void discoverAlwaysReturnsTestDescriptor() {
		TestDescriptor testDescriptor = discoverAllTests();

		assertThat(testDescriptor).isNotNull();
	}

	@Test
	public void discoverReturnsTestDescriptorWithUniqueIdAndCorrectDisplayName() {
		EngineDiscoveryRequest discoveryRequest = mock(EngineDiscoveryRequest.class);
		UniqueId uniqueId = mock(UniqueId.class);
		TestDescriptor testDescriptor = engine.discover(discoveryRequest, uniqueId);

		//TODO add non-null assumption once we have assertj-3.9.0
		assertThat(testDescriptor.getUniqueId()).isSameAs(uniqueId);
		assertThat(testDescriptor.getDisplayName()).isEqualTo("Exampilistic");
	}

	@Test
	public void discoversAllTestsFromJsonLoader() {
		TestDescriptor expectedTestDescriptor = mock(TestDescriptor.class);
		installExpectedTestDescriptorTo(expectedTestDescriptor, jsonTestLoader);

		TestDescriptor testDescriptor = discoverAllTests();

		//TODO add non-null assumption once we have assertj-3.9.0
		//Here be dragons: Cannot remove the extracting call, even though it does nothing, because otherwise the
		//method contains will not be found (some generics issue).
		assertThat(testDescriptor.getChildren()).extracting(x -> (TestDescriptor) x).contains(expectedTestDescriptor);
	}

	private TestDescriptor discoverAllTests() {
		EngineDiscoveryRequest discoveryRequest = mock(EngineDiscoveryRequest.class);
		UniqueId uniqueId = mock(UniqueId.class);
		return engine.discover(discoveryRequest, uniqueId);
	}

	private void installExpectedTestDescriptorTo(TestDescriptor expectedTestDescriptor, TestLoader jsonTestLoader) {
		ArrayList<TestDescriptor> testDescriptors = new ArrayList<>();
		testDescriptors.add(expectedTestDescriptor);
		when(this.jsonTestLoader.loadAllTests()).thenReturn(testDescriptors);
	}


	@Test
	public void discoversAllTestsFromJavaLoader() {
		TestDescriptor expectedTestDescriptor = mock(TestDescriptor.class);
		installExpectedTestDescriptorTo(expectedTestDescriptor, javaTestLoader);

		TestDescriptor testDescriptor = discoverAllTests();

		//TODO add non-null assumption once we have assertj-3.9.0
		//Here be dragons: Cannot remove the extracting call, even though it does nothing, because otherwise the
		//method contains will not be found (some generics issue).
		assertThat(testDescriptor.getChildren()).extracting(x -> (TestDescriptor) x).contains(expectedTestDescriptor);
	}
}
