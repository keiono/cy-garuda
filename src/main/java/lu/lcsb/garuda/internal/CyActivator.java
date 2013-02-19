package lu.lcsb.garuda.internal;

import java.util.Properties;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	public CyActivator() {
		super();
	}

	public void start(BundleContext bc) {

		CyApplicationManager cyApplicationManager = getService(bc, CyApplicationManager.class);

		MenuAction action = new MenuAction(cyApplicationManager, "Connect to Garuda");
		GarudaConnect connectAction = new GarudaConnect(cyApplicationManager, "Register Garuda");

		Properties properties = new Properties();

		// JOptionPane.showMessageDialog(null, "Connecting to Garuda...");

		registerAllServices(bc, action, properties);
		registerAllServices(bc, connectAction, properties);
	}

}
