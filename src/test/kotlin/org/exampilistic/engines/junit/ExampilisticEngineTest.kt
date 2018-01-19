package org.exampilistic.engines.junit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.platform.engine.UniqueId
import org.mockito.Mockito.mock
import java.lang.IllegalArgumentException

class ExampilisticEngineTest {

	lateinit var exampilisticEngine : ExampilisticEngine
	lateinit var uniqueId : UniqueId

	@BeforeEach
	fun setUp() {
		uniqueId = mock(UniqueId::class.java)
		exampilisticEngine = ExampilisticEngine()
	}


	@Test
	fun discover_returns_engine_descriptor_of_proper_type() {
		val descriptor = exampilisticEngine.discover(null, uniqueId)
		assertThat(descriptor).isInstanceOf(ExampilisticEngineDescriptor::class.java)
	}

	@Test
	fun discover_returns_engine_with_proper_unique_id() {
		val descriptor = exampilisticEngine.discover(null, uniqueId)
		assertThat(descriptor.uniqueId).isEqualTo(uniqueId)
	}

	@Test
	fun discover_throws_exception_when_uniqueId_is_missing() {
		val exception = assertThrows(IllegalArgumentException::class.java, { -> exampilisticEngine.discover(null, null) })
		assertThat(exception.message).isEqualTo("uniqueId is missing")
	}
}