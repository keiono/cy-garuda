package lu.lcsb.garuda.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.platform.commons.FileFormat;
import jp.sbi.garuda.platform.commons.exception.NetworkException;

import org.cytoscape.application.CyVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GarudaLauncher {

	private static final Logger logger = LoggerFactory.getLogger(GarudaLauncher.class);

	// Information about this client
	private static final String SOFTWARE_ID = "ea2f21c0-7a3c-11e2-b92a-0800200c9a66";
	private static final String SOFTWARE_NAME = "Cytoscape";
	private static final String PROVIDER = "LCSB";

	private static final String ICON_NAME = "cySmall.png";
	private static final String ICON_DIR = "icons";
	private static final String SS_NAME = "cyGar_ss1.png";
	private static final String SS_DIR = "screenshot";

	private static final List<String> categories = new ArrayList<String>();
	private static final String description = "Cytoscape 3 Desktop Version.";
	private static final List<String> screenshots = new ArrayList<String>();

	private static final List<FileFormat> outffs = new ArrayList<FileFormat>();
	private static final List<FileFormat> inffs = new ArrayList<FileFormat>();

	private String icon;

	private GarudaClientBackend backendGaurda;
	private final CyVersion version;
	
	GarudaLauncher(final CyVersion version) {
		this.version = version;
		init();
	}

	private void init() {

		outffs.add(new FileFormat("xml", "sbml"));
		outffs.add(new FileFormat("txt", "txt"));
		outffs.add(new FileFormat("xml", "graphml"));
		// List of the inputs for this software
		inffs.add(new FileFormat("xml", "sbml"));
		inffs.add(new FileFormat("html", "html"));
		inffs.add(new FileFormat("txt", "txt"));
		inffs.add(new FileFormat("txt", "genelist"));

		categories.add("Visualization");
		categories.add("Analytics");

		icon = createTempFiles(ICON_NAME, ICON_DIR).toString();
		screenshots.add(createTempFiles(SS_NAME, SS_DIR).toString());
	}

	private File createTempFiles(final String imageFileName, final String dirName) {
		File tempFile = null;
		try {
			tempFile = File.createTempFile(imageFileName, ".png");
		} catch (IOException e) {
			logger.error("Could not create temp file for images.", e);
		}
		tempFile.deleteOnExit();

		try {
			ImageIO.write(
					ImageIO.read(GarudaLauncher.class.getClassLoader().getResource(
							"/" + SOFTWARE_ID + "/" + dirName + "/" + imageFileName)), "png", tempFile);
		} catch (IOException e) {
			logger.error("Could not write temp image file.", e);
		}

		return tempFile;
	}

	final GarudaClientBackend getClientBackend() {

		if (backendGaurda == null) {
			try {
				backendGaurda = new GarudaClientBackend(SOFTWARE_ID, SOFTWARE_NAME + " " + version.getVersion(), icon, outffs, inffs, categories,
						PROVIDER, description, screenshots);

			} catch (NetworkException e) {
				logger.error("Could not initialize GarudaClientBackend service.", e);
			}
		}

		return backendGaurda;
	}
}