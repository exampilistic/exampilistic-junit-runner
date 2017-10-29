package org.exampilistic.engines.junit;

import org.junit.platform.engine.TestDescriptor;

import java.util.ArrayList;
import java.util.Collection;

public class TestsMerger {
	public static Collection<TestDescriptor> mergeTests(Collection<TestDescriptor> testDescriptorsFromJson, Collection<TestDescriptor> testDescriptorsFromClasses) {
		ArrayList<TestDescriptor> result = new ArrayList<>();
		result.addAll(testDescriptorsFromJson);
		result.addAll(testDescriptorsFromClasses);
		return result;
	}
}
