package org.exampilistic.engines.junit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.platform.engine.UniqueId
import org.mockito.Mockito.`when` as whenCalled
import org.mockito.Mockito.mock
import java.lang.IllegalArgumentException

internal class ExampilisticEngineTest {

	lateinit var exampilisticEngine : ExampilisticEngine
	lateinit var uniqueId : UniqueId
	lateinit var testGroupLoader : TestGroupLoader
	lateinit var streamLoader: StreamLoader

	@BeforeEach
	fun setUp() {
		streamLoader = mock(StreamLoader::class.java)
		testGroupLoader = TestGroupLoader(streamLoader)
		uniqueId = mock(UniqueId::class.java)
		exampilisticEngine = ExampilisticEngine(testGroupLoader)
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


	@Test
	fun descriptor_has_no_children() {
		val descriptor = exampilisticEngine.discover(null, uniqueId)
		assertThat(descriptor.children).isEmpty()
	}

	@Test
	fun descriptor_has_one_child_when_testGroupLoader_returned_child() {
		whenCalled(streamLoader.streams).thenReturn(listOf(Object()))
		val descriptor = exampilisticEngine.discover(null, uniqueId)
		assertThat(descriptor.children).hasSize(1)
	}

}