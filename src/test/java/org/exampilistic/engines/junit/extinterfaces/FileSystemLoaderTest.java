package org.exampilistic.engines.junit.extinterfaces;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class FileSystemLoaderTest {
	private FileSystemLoader fileSystemLoader;
	private File tmpDirectory;

	@BeforeEach
	public void setup() {
		this.fileSystemLoader = new FileSystemLoader();
		tmpDirectory = new File("tmpForTests");
		tmpDirectory.delete();
		tmpDirectory.mkdirs();
	}

	@AfterEach
	public void teardown() {
		tmpDirectory.delete();
	}

	@Test
	public void findsAllPathsMatchingPattern() {
		new File(tmpDirectory, "f1.p").mkdirs();
		new File(tmpDirectory, "f2.p").mkdirs();
		new File(tmpDirectory, "f3.z").mkdirs();

		Collection<Path> paths = fileSystemLoader.find(tmpDirectory.toPath(), "*.p");

		List<String> fileNames = paths.stream().map(Path::getFileName).map(Path::toString).collect(Collectors.toList());
		assertThat(fileNames).containsExactly("f1.p", "f2.p");
	}

	@Test
	public void opensReaderForGivenPath() {
		File file = new File(tmpDirectory, "example.txt");
		try(PrintWriter writer = new PrintWriter(new FileWriter(file))) {
			writer.println("Test Data");
		} catch (IOException e) {
			fail("Could not open example file for writing test data", e);
		}

		try(BufferedReader reader = new BufferedReader(fileSystemLoader.openReader(file.toPath()))) {
			String line = reader.readLine();

			assertThat(line).isEqualTo("Test Data");
		} catch (IOException e) {
			fail("Could not open example file for reading", e);
		}
	}
}