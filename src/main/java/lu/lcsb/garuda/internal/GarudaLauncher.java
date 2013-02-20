package lu.lcsb.garuda.internal;

import java.util.ArrayList;
import java.util.List;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.platform.commons.FileFormat;
import jp.sbi.garuda.platform.commons.exception.NetworkException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GarudaLauncher {

	private static final Logger logger = LoggerFactory.getLogger(GarudaLauncher.class);

	// Information about this client
	private static final String SOFTWARE_ID = "ea2f21c0-7a3c-11e2-b92a-0800200c9a66";
	private static final String SOFTWARE_NAME = "CyGaruda";
	private static final String PROVIDER = "LCSB";

	private static List<String> categories;
	private static String description;
	private static List<String> screenshots;
	private static String ICON_PATH;
	private static List<FileFormat> outffs, inffs;

	private GarudaClientBackend backendGaurda;

	GarudaLauncher() {
		init();
	}

	private void init() {

		outffs = new ArrayList<FileFormat>();
		outffs.add(new FileFormat("xml", "sbml"));
		outffs.add(new FileFormat("txt", "txt"));
		// List of the inputs for this software
		inffs = new ArrayList<FileFormat>();
		inffs.add(new FileFormat("xml", "sbml"));
		inffs.add(new FileFormat("html", "html"));
		inffs.add(new FileFormat("txt", "txt"));

		categories = new ArrayList<String>();
		categories.add("Simulation");
		categories.add("Optimization");

		description = "TSA Description";
		screenshots = new ArrayList<String>();

		ICON_PATH = GarudaLauncher.class.getClassLoader().getResource("/" + SOFTWARE_ID +"/icons/cySmall.png").toString();
		screenshots.add(GarudaLauncher.class.getClassLoader().getResource("/" + SOFTWARE_ID +"/snapshot/cyGar_ss1.png").toString());
		// screenshots.add(folder + "Screen2.png");
		// screenshots.add(folder + "Screen3.png");

	}

	final GarudaClientBackend getClientBackend() {

		if (backendGaurda == null) {
			try {
				backendGaurda = new GarudaClientBackend(SOFTWARE_ID, SOFTWARE_NAME, ICON_PATH, outffs, inffs,
						categories, PROVIDER, description, screenshots);

			} catch (NetworkException e) {
				logger.error("Could not initialize GarudaClientBackend service.", e);
			}
		}

		return backendGaurda;
	}
}