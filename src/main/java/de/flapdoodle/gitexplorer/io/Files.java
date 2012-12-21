package de.flapdoodle.gitexplorer.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.UUID;

public class Files {

	public static boolean deleteAll(File fileOrDirectory) {
		if (fileOrDirectory.exists()) {
			if (fileOrDirectory.isDirectory()) {
				File[] files = fileOrDirectory.listFiles();
				for (File f : files) {
					if (!deleteAll(f)) {
						return false;
					}
				}
				return fileOrDirectory.delete();
			} else {
				return fileOrDirectory.delete();
			}
		}
		return false;
	}

	public static File createTempDir(String prefix) throws IOException {
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		File tempFile = new File(tempDir, prefix + "-" + UUID.randomUUID().toString());
		if (!tempFile.mkdir())
			throw new IOException("Could not create Tempdir: " + tempFile);
		return tempFile;
	}

	public static void write(String content, File output) throws IOException {
		FileOutputStream out = new FileOutputStream(output);
		OutputStreamWriter w = new OutputStreamWriter(out);

		try {
			w.write(content);
			w.flush();
		} finally {
			out.close();
		}
	}
}
