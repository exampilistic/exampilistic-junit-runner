package org.exampilistic.engines.junit;

import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.support.descriptor.EngineDescriptor;

public class ExampilisticEngineDescriptor extends EngineDescriptor {
	public ExampilisticEngineDescriptor(UniqueId uniqueId) {
		super(uniqueId, "Exampilistic");
	}
}
