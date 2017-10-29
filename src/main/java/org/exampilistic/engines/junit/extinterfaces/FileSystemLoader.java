package org.exampilistic.engines.junit.extinterfaces;

import net.davidtanzer.jdefensive.Args;
import net.davidtanzer.jdefensive.Assert;
import net.davidtanzer.jdefensive.Returns;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class FileSystemLoader {
	public Collection<Path> find(Path directory, String pattern) {
		Args.notNull(directory, "directory");
		Assert.is(directory, Files::exists, __ -> new IllegalArgumentException("directory must exist!"));
		Assert.is(directory, Files::isDirectory, __ -> new IllegalArgumentException("directory must be a directory!"));
		Args.notEmpty(pattern, "pattern");

		try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory, pattern)) {
			return Returns.notNull(listOfPathsFrom(directoryStream));
		} catch (IOException e) {
			throw new IllegalStateException("Could not list directory", e);
		}
	}

	public Reader openReader(Path filePath) throws IOException {
		return Returns.notNull(Files.newBufferedReader(filePath));
	}

	private Collection<Path> listOfPathsFrom(DirectoryStream<Path> directoryStream) {
		assert directoryStream != null : "Assuming Files will always return non-null stream for an existing directory...";
		ArrayList<Path> paths = new ArrayList<>();

		for(Path path : directoryStream) {
			assert path != null : "Assuming that all paths in a directory stream are non-null...";
			paths.add(path);
		}

		return Returns.notNull(paths);
	}

}
