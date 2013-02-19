package lu.lcsb.garuda.internal;

import java.util.Properties;

import javax.swing.JOptionPane;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.service.util.AbstractCyActivator;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		
		CyApplicationManager cyApplicationManager = getService(context, CyApplicationManager.class);
		
		MenuAction action = new MenuAction(cyApplicationManager, "Connect to Garuda");
		GarudaConnect connectAction = new GarudaConnect(cyApplicationManager, "Register Garuda");
		
		Properties properties = new Properties();
		
		//JOptionPane.showMessageDialog(null, "Connecting to Garuda...");
		
		registerAllServices(context, action, properties);
		registerAllServices(context, connectAction, properties);
	}

}
