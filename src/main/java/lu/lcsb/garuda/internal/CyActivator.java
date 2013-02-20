package lu.lcsb.garuda.internal;

import java.util.Properties;

import jp.sbi.garuda.client.backend.GarudaClientBackend;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.CyVersion;
import org.cytoscape.service.util.AbstractCyActivator;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	public CyActivator() {
		super();
	}

	@Override
	public void start(BundleContext bc) {

		// Service imports
		final CyApplicationManager cyApplicationManager = getService(bc, CyApplicationManager.class);
		final CyVersion version = getService(bc, CyVersion.class);
		
		// Garuda service objects
		final GarudaLauncher garudaLauncher = new GarudaLauncher(version);
		final GarudaClientBackend backendGaurda = garudaLauncher.getClientBackend();

		//MenuAction action = new MenuAction(cyApplicationManager, "Connect to Garuda");
		final GarudaRegister connectAction = new GarudaRegister(cyApplicationManager, "Register Garuda", backendGaurda);

		//registerAllServices(bc, action, properties);
		registerAllServices(bc, connectAction, new Properties());
	}
}
