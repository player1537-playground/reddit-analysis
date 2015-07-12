package com.github.player1537.reddit;

import java.util.Set;
import java.util.HashSet;
import java.nio.files.Paths;
import java.nio.files.Files;
import java.nio.files.Path;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RedditInputStream {
	private RedditInputStream() {
	}

	private static Set<Path> getPaths(Configuration config) {
		Set<Path> paths = new HashSet<Path>();

		Path rootDirectory = Paths.get(config.get("reddit.root.directory"));

		for (String year : config.get("reddit.years").split(",")) {
			Path path = rootDirectory.resolve(year);
			if (!Files.isDirectory(path)) {
				continue;
			}

			for (Path filePath : Files.newDirectoryStream(path, "*.bz2")) {
				paths.add(filePath);
			}
		}

		return paths;
	}

	private static InputStream loadFile(Path path) {
		InputStream inputStream
			= Files.newInputStream(path, StandardOpenOptions.READ);

		Bzip2CompressorInputStream bzIn
			= new Bzip2CompressorInputStream(inputStream);

		return bzIn;
	}

	public static BufferedReader getReader(Configuration config) {
		Set<Path> paths = RedditInputStream.getPaths(config);

		List<InputStream> inputStreams = new ArrayList<>();
		for (Path path : paths) {
			inputStreams.add(RedditInputStream.loadFile(path));
		}

		InputStream inputStream
			= new SequenceInputStream(Collections.enumeration(inputStreams));

		InputStreamReader inputStreamReader
			= new InputStreamReader(inputStream, "UTF-8");

		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		return bufferedReader;
	}


}
