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

		//MenuAction action = new MenuAction(cyApplicationManager, "Connect to Garuda");
		GarudaRegister connectAction = new GarudaRegister(cyApplicationManager, "Register Garuda");

		Properties properties = new Properties();

		//registerAllServices(bc, action, properties);
		registerAllServices(bc, connectAction, properties);
	}
}
