package lu.lcsb.garuda.internal;

import java.util.Properties;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import lu.lcsb.garuda.internal.task.RegisterGarudaTaskFactory;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.CyVersion;
import org.cytoscape.property.CyProperty;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.swing.DialogTaskManager;
import org.osgi.framework.BundleContext;

public class CyActivator extends AbstractCyActivator {

	private static final String GARUDA_REGISTERED = "garuda.registered";

	public CyActivator() {
		super();
	}

	@Override
	public void start(BundleContext bc) {

		// Service imports
		final CyApplicationManager cyApplicationManager = getService(bc, CyApplicationManager.class);
		final CyVersion version = getService(bc, CyVersion.class);
		final DialogTaskManager taskManager = getService(bc, DialogTaskManager.class);
		final CyProperty<Properties> cytoscapePropertiesServiceRef = getService(bc, CyProperty.class,
				"(cyPropertyName=cytoscape3.props)");

		// Garuda service objects
		final GarudaLauncher garudaLauncher = new GarudaLauncher(version);
		final GarudaClientBackend backendGaurda = garudaLauncher.getClientBackend();

		final Properties props = cytoscapePropertiesServiceRef.getProperties();
		final String isRegistered = props.getProperty(GARUDA_REGISTERED);
		if (isRegistered == null) {
			final TaskFactory registerGarudaTaskFactory = new RegisterGarudaTaskFactory(backendGaurda);
			taskManager.execute(registerGarudaTaskFactory.createTaskIterator());
			props.setProperty(GARUDA_REGISTERED, "true");

		}

//		final GarudaRegister connectAction = new GarudaRegister(cyApplicationManager, "Register Garuda", backendGaurda,
//				taskManager);
//
//		registerAllServices(bc, connectAction, new Properties());
	}
}
