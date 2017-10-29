package org.exampilistic.engines.junit;

import net.davidtanzer.jdefensive.Args;
import net.davidtanzer.jdefensive.Returns;
import org.exampilistic.engines.junit.javatests.JavaTestLoader;
import org.exampilistic.engines.junit.jsontests.JsonTestLoader;
import org.junit.platform.engine.*;

import java.util.Collection;

public class ExampilisticEngine implements TestEngine {
	private final JsonTestLoader jsonTestLoader;
	private final JavaTestLoader javaTestLoader;

	public ExampilisticEngine() {
		this(new JsonTestLoader(), new JavaTestLoader());
	}

	public ExampilisticEngine(JsonTestLoader jsonTestLoader, JavaTestLoader javaTestLoader) {
		Args.notNull(jsonTestLoader, "jsonTestLoader");
		Args.notNull(javaTestLoader, "javaTestLoader");

		this.jsonTestLoader = jsonTestLoader;
		this.javaTestLoader = javaTestLoader;
	}

	@Override
	public String getId() {
		return Returns.notNull("exampilistic");
	}

	@Override
	public TestDescriptor discover(EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
		Args.notNull(discoveryRequest, "discoveryRequest");
		Args.notNull(uniqueId, "uniqueId");

		Collection<TestDescriptor> testDescriptorsFromJson = this.jsonTestLoader.loadAllTests();
		assert testDescriptorsFromJson != null : "Json test loader must always return a value";
		Collection<TestDescriptor> testDescriptorsFromClasses = this.javaTestLoader.loadAllTests();
		assert testDescriptorsFromClasses != null : "Java test loader must always return a value";

		ExampilisticEngineDescriptor engineDescriptor = new ExampilisticEngineDescriptor(uniqueId);
		Collection<TestDescriptor> testDescriptors = TestsMerger.mergeTests(
				testDescriptorsFromJson, testDescriptorsFromClasses);
		assert testDescriptors != null : "Tests merger must always return a value";

		testDescriptors.forEach(testDescriptor -> engineDescriptor.addChild(testDescriptor));

		return Returns.notNull(engineDescriptor);
	}

	@Override
	public void execute(ExecutionRequest request) {

	}
}
