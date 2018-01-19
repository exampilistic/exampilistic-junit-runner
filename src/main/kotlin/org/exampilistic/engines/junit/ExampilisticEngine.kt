package org.exampilistic.engines.junit

import org.junit.platform.engine.*
import org.junit.platform.engine.support.descriptor.EngineDescriptor
import java.lang.Exception

class ExampilisticEngine : TestEngine {

	override fun discover(discoveryRequest: EngineDiscoveryRequest?, uniqueId: UniqueId?): EngineDescriptor {
		if (uniqueId != null) {
			return ExampilisticEngineDescriptor(uniqueId)
		} else {
			throw IllegalArgumentException("uniqueId is missing")
		}
	}

	override fun getId(): String {
		return this.toString()
	}

	override fun execute(request: ExecutionRequest?) {
	}


}