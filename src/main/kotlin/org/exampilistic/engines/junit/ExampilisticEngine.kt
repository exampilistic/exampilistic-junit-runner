package org.exampilistic.engines.junit

import org.junit.platform.engine.*
import org.junit.platform.engine.support.descriptor.EngineDescriptor

class ExampilisticEngine(testGroupLoader: TestGroupLoader = TestGroupLoader()) : TestEngine {

	private val testGroupLoader = testGroupLoader


	override fun discover(discoveryRequest: EngineDiscoveryRequest?, uniqueId: UniqueId?): EngineDescriptor {
		if (uniqueId == null) {
			throw IllegalArgumentException("uniqueId is missing")
		}
		val exampilisticEngineDescriptor = ExampilisticEngineDescriptor(uniqueId)
		this.testGroupLoader.groups.forEach { item ->
			exampilisticEngineDescriptor.addChild(item)
		}
		return exampilisticEngineDescriptor
	}

	override fun getId(): String {
		return this.toString()
	}

	override fun execute(request: ExecutionRequest?) {
	}


}