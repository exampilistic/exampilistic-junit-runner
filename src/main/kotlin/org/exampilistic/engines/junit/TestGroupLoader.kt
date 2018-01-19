package org.exampilistic.engines.junit

import org.junit.platform.engine.TestDescriptor
import org.junit.platform.engine.TestSource
import org.junit.platform.engine.TestTag
import org.junit.platform.engine.UniqueId
import java.util.*

public class TestGroupLoader(streamLoader: StreamLoader = FileStreamLoader()) {
	private val streamLoader = streamLoader
	public val groups : List<TestGroup>
	get() {
		return streamLoader.streams.map({_ -> object : TestGroup {
			override fun removeFromHierarchy() {
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

			override fun setParent(parent: TestDescriptor?) {
			}

			override fun getParent(): Optional<TestDescriptor> {
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

			override fun getChildren(): MutableSet<out TestDescriptor> {
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

			override fun getDisplayName(): String {
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

			override fun getType(): TestDescriptor.Type {
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

			override fun getUniqueId(): UniqueId {
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

			override fun removeChild(descriptor: TestDescriptor?) {
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

			override fun getTags(): MutableSet<TestTag> {
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

			override fun findByUniqueId(uniqueId: UniqueId?): Optional<out TestDescriptor> {
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

			override fun addChild(descriptor: TestDescriptor?) {
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}

			override fun getSource(): Optional<TestSource> {
				TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
			}
		} })
	}

}

