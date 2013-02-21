package lu.lcsb.garuda.internal.util;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TempFileGenerator {
	
	private static final Logger logger = LoggerFactory.getLogger(TempFileGenerator.class);

	public File createTempFile(final String fileName, final String fileExt) {
		File tempFile = null;
		try {
			tempFile = File.createTempFile(fileName, fileExt);
		} catch (IOException e) {
			logger.error("Could not create temp file for images.", e);
		}
		tempFile.deleteOnExit();
		
		return tempFile;
	}
}
