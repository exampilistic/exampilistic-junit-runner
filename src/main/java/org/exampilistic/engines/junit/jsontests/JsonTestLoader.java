package org.exampilistic.engines.junit.jsontests;

import net.davidtanzer.jdefensive.Args;
import net.davidtanzer.jdefensive.Returns;
import org.exampilistic.engines.junit.TestLoader;
import org.exampilistic.engines.junit.extinterfaces.FileSystemLoader;
import org.exampilistic.engines.junit.extinterfaces.JsonParser;
import org.junit.platform.engine.TestDescriptor;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonTestLoader implements TestLoader {
	private final TestDataGenerators testDataGenerators;
	private final FileSystemLoader fileSystemLoader;
	private final JsonParser jsonParser;
	private final Path testsPath = FileSystems.getDefault().getPath("testdata");;

	public JsonTestLoader() {
		this(new FileSystemLoader(), new JsonParser(), new TestDataGenerators());
	}

	public JsonTestLoader(FileSystemLoader fileSystemLoader, JsonParser jsonParser, TestDataGenerators testDataGenerators) {
		Args.notNull(fileSystemLoader, "fileSystemLoader");
		Args.notNull(jsonParser, "jsonParser");
		Args.notNull(testDataGenerators, "testDataGenerators");

		this.fileSystemLoader = fileSystemLoader;
		this.jsonParser = jsonParser;
		this.testDataGenerators = testDataGenerators;
	}

	@Override
	public Collection<TestDescriptor> loadAllTests() {
		Collection<Path> jsonFilePaths = fileSystemLoader.find(this.testsPath, "*.json");
		assert jsonFilePaths != null : "File system loader must always return a non-null collection.";

		return Returns.notNull(jsonFilePaths.stream()
				.map(this::loadJson)
				.map(testDataGenerators::generate)
				.collect(Collectors.toList()));
	}

	private Map<String, Object> loadJson(Path path) {
		assert path != null : "File system loader must only include non-null paths in the result collection, so here we must have a value.";

		try(Reader reader = fileSystemLoader.openReader(path)) {
			Map<String, Object> parsedJson = jsonParser.fromJson(reader);

			assert parsedJson != null : "Json parser must always return a non-null map, so we always return a value.";
			return parsedJson;
		} catch (IOException e) {
			throw new IllegalStateException("Could not read json file", e);
		}
	}
}
