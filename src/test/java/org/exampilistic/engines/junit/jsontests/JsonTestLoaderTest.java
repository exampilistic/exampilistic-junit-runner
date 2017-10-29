package org.exampilistic.engines.junit.jsontests;

import org.exampilistic.engines.junit.extinterfaces.FileSystemLoader;
import org.exampilistic.engines.junit.extinterfaces.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestDescriptor;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

class JsonTestLoaderTest {
	private FileSystemLoader fileSystemLoader;
	private JsonTestLoader jsonTestLoader;
	private JsonParser jsonParser;
	private TestDataGenerators testDataGenerators;

	@BeforeEach
	void setup() {
		this.fileSystemLoader = mock(FileSystemLoader.class);
		this.jsonParser = mock(JsonParser.class);
		this.testDataGenerators = mock(TestDataGenerators.class);
		this.jsonTestLoader = new JsonTestLoader(fileSystemLoader, jsonParser, testDataGenerators);
	}

	@Test
	public void opensAllFoundJsonFilesFromTheFileSystem() throws IOException {
		Path filePath = mock(Path.class);
		List<Path> jsonFiles = Arrays.asList(filePath);
		when(fileSystemLoader.find(any(), eq("*.json"))).thenReturn(jsonFiles);

		jsonTestLoader.loadAllTests();

		verify(fileSystemLoader).openReader(filePath);
	}

	@Test
	public void loadsAllFoundFilesAsJson() throws IOException {
		Path filePath = mock(Path.class);
		List<Path> jsonFiles = Arrays.asList(filePath);
		Reader reader = mock(Reader.class);
		when(fileSystemLoader.find(any(), eq("*.json"))).thenReturn(jsonFiles);
		when(fileSystemLoader.openReader(any())).thenReturn(reader);

		jsonTestLoader.loadAllTests();

		verify(jsonParser).fromJson(reader);
	}

	@Test
	public void passesParsedJsonDataToTestDataGenerator() {
		Path filePath = mock(Path.class);
		List<Path> jsonFiles = Arrays.asList(filePath);
		when(fileSystemLoader.find(any(), eq("*.json"))).thenReturn(jsonFiles);
		Map<String, Object> jsonData = new HashMap<>();
		when(jsonParser.fromJson(any())).thenReturn(jsonData);
		TestDescriptor descriptor = mock(TestDescriptor.class);
		when(testDataGenerators.generate(jsonData)).thenReturn(descriptor);

		Collection<TestDescriptor> loadedTests = jsonTestLoader.loadAllTests();

		assertThat(loadedTests).contains(descriptor);
	}
}